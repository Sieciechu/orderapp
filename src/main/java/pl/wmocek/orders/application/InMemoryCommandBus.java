package pl.wmocek.orders.application;

import pl.wmocek.orders.application.command.*;
import pl.wmocek.orders.application.command.handlers.CreateAllOrdersCSVReportHandlerFactory;
import pl.wmocek.orders.application.command.handlers.CreateAllOrdersScreenReportHandlerFactory;
import pl.wmocek.orders.io.OrderReader;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class InMemoryCommandBus implements CommandBus {

    private Map<String, Supplier<Handler>> resolver = new HashMap<>();

    InMemoryCommandBus(OrderReader orderReader){



        setResolverEntry(CreateAllOrdersScreenReport.class,
            () -> { return new CreateAllOrdersScreenReportHandlerFactory(orderReader).create(); });

        setResolverEntry(CreateAllOrdersCSVReport.class,
            () -> { return new CreateAllOrdersCSVReportHandlerFactory(orderReader).create(); });
    }

    private void setResolverEntry(Class<? extends Command> key, Supplier<Handler> supplier) {
        resolver.put(key.getCanonicalName(), supplier);
    }


    @Override
    public void send(Command c) throws Exception {
        Handler h = resolver.get(c.getClass().getCanonicalName()).get();
        h.handle(c);
        System.out.println("Senddddd");
    }
}
