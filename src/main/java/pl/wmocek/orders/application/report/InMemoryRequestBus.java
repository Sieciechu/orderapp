package pl.wmocek.orders.application.report;

import pl.wmocek.orders.application.report.handlers.Handler;
import pl.wmocek.orders.application.report.request.*;
import pl.wmocek.orders.domain.OrdersRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class InMemoryRequestBus implements RequestBus {

    private Map<String, Supplier<Handler>> resolver = new HashMap<>();


    InMemoryRequestBus(OrdersRepository ordersRepository){

        setResolverEntry(ListAllOrders.class,
            () -> new pl.wmocek.orders.application.report.handlers.ListAllOrders(ordersRepository));

        setResolverEntry(CountAllOrders.class,
            () -> new pl.wmocek.orders.application.report.handlers.CountAllOrders(ordersRepository));

        setResolverEntry(AveragePriceOfAllOrders.class,
            () -> new pl.wmocek.orders.application.report.handlers.AveragePriceOfAllOrders(ordersRepository));

        setResolverEntry(TotalPriceOfAllOrders.class,
            () -> new pl.wmocek.orders.application.report.handlers.TotalPriceOfAllOrders(ordersRepository));

        setResolverEntry(CountAllOrdersForCustomer.class,
            () -> new pl.wmocek.orders.application.report.handlers.CountAllOrdersForCustomer(ordersRepository));

        setResolverEntry(AveragePriceOfAllOrdersForCustomer.class,
            () -> new pl.wmocek.orders.application.report.handlers.AveragePriceOfAllOrdersForCustomer(ordersRepository));

        setResolverEntry(TotalPriceOfAllOrdersForCustomer.class,
            () -> new pl.wmocek.orders.application.report.handlers.TotalPriceOfAllOrdersForCustomer(ordersRepository));

        setResolverEntry(ListAllOrdersForCustomer.class,
            () -> new pl.wmocek.orders.application.report.handlers.ListAllOrdersForCustomer(ordersRepository));
    }

    private void setResolverEntry(Class<? extends Request> key, Supplier<Handler> supplier) {
        resolver.put(key.getCanonicalName(), supplier);
    }


    @Override
    public Report send(Request c) throws Exception {
        Handler h = getHandler(c);
        return h.handle(c);
    }

    private Handler getHandler(Request c) {
        return resolver.get(c.getClass().getCanonicalName()).get();
    }
}
