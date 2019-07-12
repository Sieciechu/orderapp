
package pl.wmocek.ordersapp;

import lombok.NonNull;
import pl.wmocek.orders.application.report.*;
import pl.wmocek.orders.application.report.request.Request;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.infrastructure.FileReaderResolver;
import pl.wmocek.orders.infrastructure.InMemoryOrdersRepository;
import pl.wmocek.orders.infrastructure.Reader;
import pl.wmocek.orders.infrastructure.ReaderException;
import pl.wmocek.orders.infrastructure.writer.FileWriterFactory;
import pl.wmocek.orders.infrastructure.writer.ScreenWriterFactory;
import pl.wmocek.orders.infrastructure.writer.Writer;



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
//            String out = reportMapper.map(report, request.getData("destination"));
            Writer w = resolveWriter(request);

         //   w.write(report);
        }

    }

    private Writer resolveWriter(Request request) throws Exception {
        Writer w;
        String fileName = request.getData("fileName");
        String type = request.getData("destination");

        if ("screen".equals(type)) {
            return new ScreenWriterFactory().create();
        }

        if ("file".equals(type)) {
            if (null == fileName) {
                throw new Exception("Filename is missing");
            }
            return new FileWriterFactory(fileName).create();
        }

        throw new Exception("Cannot create writer for given request");

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
