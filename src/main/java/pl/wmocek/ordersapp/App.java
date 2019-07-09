
package pl.wmocek.ordersapp;

import lombok.NonNull;
import pl.wmocek.orders.application.CliControllerFactory;
import pl.wmocek.orders.application.CommandBus;
import pl.wmocek.orders.application.CommandBusFactory;
import pl.wmocek.orders.application.Controller;
import pl.wmocek.orders.application.command.Command;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.infrastructure.FileReaderResolver;
import pl.wmocek.orders.infrastructure.InMemoryOrdersRepository;
import pl.wmocek.orders.infrastructure.Reader;
import pl.wmocek.orders.infrastructure.ReaderException;

public class App {

    private final String FILES_DIR_PATH = "/home/wojciech/Projects/orderapp2/src/main/resources/";
    private OrdersRepository ordersRepository;
    private FileReaderResolver readerFactory;
    private Controller controller;
    private CommandBus commandBus;

    private App(
        @NonNull OrdersRepository o,
        @NonNull FileReaderResolver r,
        @NonNull Controller c,
        @NonNull CommandBus cb
    ) {
        ordersRepository = o;
        readerFactory = r;
        controller = c;
        commandBus = cb;
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

    private void run() throws Exception {
        while(true){
            Command c = controller.getCommand();
            if (null == c) {
                break;
            }

            commandBus.send(c);
        }

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
