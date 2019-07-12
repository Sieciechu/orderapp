
package pl.wmocek.ordersapp;

import lombok.NonNull;
import pl.wmocek.orders.application.report.*;
import pl.wmocek.orders.application.report.request.ListRequest;
import pl.wmocek.orders.application.report.request.Request;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.infrastructure.InMemoryOrdersRepository;
import pl.wmocek.orders.infrastructure.ReaderException;
import pl.wmocek.orders.infrastructure.ReportWriter;
import pl.wmocek.orders.infrastructure.Writer;
import pl.wmocek.orders.infrastructure.reader.FileReaderResolver;
import pl.wmocek.orders.infrastructure.reader.Reader;
import pl.wmocek.orders.infrastructure.stringer.*;
import pl.wmocek.io.FileWriterFactory;
import pl.wmocek.io.ScreenWriterFactory;

public class App {

    private final String FILES_DIR_PATH = "/home/wojciech/Projects/orderapp2/src/main/resources/";
    private OrdersRepository ordersRepository;
    private FileReaderResolver readerFactory;
    private Controller controller;
    private RequestBus requestBus;


    private App(
        @NonNull OrdersRepository o,
        @NonNull FileReaderResolver r,
        @NonNull Controller c,
        @NonNull RequestBus cb
    ) {
        ordersRepository = o;
        readerFactory = r;
        controller = c;
        requestBus = cb;
    }

    public static void main(String[] args) throws Exception {

        InMemoryOrdersRepository ordersRepository = new InMemoryOrdersRepository();
        var app = new App(
            ordersRepository,
            new FileReaderResolver(),
            new CliControllerFactory(ordersRepository).create(),
            new CommandBusFactory(ordersRepository).create()
        );
        app.feedOrdersRepository(args);
        app.run();
    }

    public void run() throws Exception {
        while(true){
            Request request = controller.getRequest();
            if (null == request) {
                break;
            }
            
            Report report = requestBus.send(request);

            Writer reportWriter = resolveWriter(request);

            reportWriter.write(report);
        }

    }

    private Writer resolveWriter(Request request) throws Exception {

        pl.wmocek.io.Writer w;
        ReportStringer reportStringer;
        pl.wmocek.orders.infrastructure.Writer reportWriter;


        String fileName = request.getData("fileName");
        String type = request.getData("destination");

        if ("screen".equals(type)) {
            w = new ScreenWriterFactory().create();

            if (request instanceof ListRequest) {
                reportStringer = new ListReportScreenStringer(new OrderScreenFormatter());
            } else {
                reportStringer = new ValueReportScreenStringer();
            }

        } else if ("file".equals(type)) {
            if (null == fileName) {
                throw new Exception("Filename is missing");
            }
            w = new FileWriterFactory(fileName).create();

            String separator = ",";
            if (request instanceof ListRequest) {
                reportStringer = new ListReportCSVStringer(separator, new OrderCSVFileFormatter(separator));
            } else {
                reportStringer = new ValueReportCSVStringer();
            }
        } else {
            throw new Exception("Cannot create writer for given request");
        }
        return new ReportWriter(w, reportStringer);
    }

    private void feedOrdersRepository(@NonNull String[] fileNames) {
        for (String fileName : fileNames) {
            Reader reader = null;
            try {
                reader = readerFactory.createReader(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Order o = null;
            while (reader.hasNext()) {
                try {

                    o = reader.read();
                    if (null == o) {
                        break;
                    }

                    ordersRepository.add(o);

                } catch (ReaderException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
