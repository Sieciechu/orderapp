package pl.wmocek.orders.application;

import pl.wmocek.orders.application.command.*;
import pl.wmocek.orders.application.command.handlers.*;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.OrderReader;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class InMemoryCommandBus implements CommandBus {

    private Map<String, Supplier<Handler>> resolver = new HashMap<>();

    InMemoryCommandBus(OrderReader orderReader, OrdersRepository ordersRepository){

        setResolverEntry(ListAllOrdersScreenReport.class,
            () -> new ListAllOrdersScreenReportFactory(orderReader).create());

        setResolverEntry(ListAllOrdersCSVReport.class,
            () -> new ListAllOrdersCSVReportFactory(orderReader).create());

        setResolverEntry(CountAllOrdersScreenReport.class,
            () -> new CountAllOrdersScreenReportFactory(ordersRepository).create());

        setResolverEntry(AveragePriceOfAllOrdersScreenReport.class,
            () -> new AveragePriceOfAllOrdersScreenReportFactory(ordersRepository).create());

        setResolverEntry(TotalPriceOfAllOrdersScreenReport.class,
            () -> new TotalPriceOfAllOrdersScreenReportFactory(ordersRepository).create());

        setResolverEntry(CountAllOrdersForCustomerScreenReport.class,
            () -> new CountAllOrdersForCustomerScreenReportFactory(ordersRepository).create());

        setResolverEntry(AveragePriceOfAllOrdersForCustomerScreenReport.class,
            () -> new AveragePriceOfAllOrdersForCustomerScreenReportFactory(ordersRepository).create());

        setResolverEntry(TotalPriceOfAllOrdersForCustomerScreenReport.class,
            () -> new TotalPriceOfAllOrdersForCustomerScreenReportFactory(ordersRepository).create());

        setResolverEntry(ListAllOrdersForCustomerScreenReport.class,
            () -> new ListAllOrdersForCustomerScreenReportFactory(ordersRepository).create());
    }

    private void setResolverEntry(Class<? extends Command> key, Supplier<Handler> supplier) {
        resolver.put(key.getCanonicalName(), supplier);
    }


    @Override
    public void send(Command c) throws Exception {
        Handler h = getHandler(c);
        h.handle(c);
    }

    private Handler getHandler(Command c) {
        return resolver.get(c.getClass().getCanonicalName()).get();
    }
}
