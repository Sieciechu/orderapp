package pl.wmocek.orders.application.report;

import pl.wmocek.orders.application.report.handlers.Handler;
import pl.wmocek.orders.application.report.handlers.HandlerFactory;
import pl.wmocek.orders.application.report.request.*;
import pl.wmocek.orders.domain.OrdersRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * InMemoryRequestBus class
 */
public class InMemoryRequestBus implements RequestBus {

    /**
     * resolver maps Requests to Request Handlers.
     * Map key is the Request class name, value is the HandlerFactory function interface for creating Request Handler
     * @see InMemoryRequestBus#setResolverEntry(Class, HandlerFactory)
     */
    private Map<String, HandlerFactory> resolver = new HashMap<>();

    /**
     * InMemoryRequestBus constructor has already hardcoded resolver dependency - meaning
     *  it has already defined mapping of Requests to Request Handlers
     * @param ordersRepository OrderRepository needed by underlying handlers
     */
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

    /**
     * Support method for mapping the request class to request handler
     * @param key
     * @param factory
     * Example: see the constructor
     */
    private void setResolverEntry(Class<? extends Request> key, HandlerFactory factory) {
        resolver.put(key.getCanonicalName(), factory);
    }


    @Override
    public Report send(Request c) throws Exception {
        Handler h = getHandler(c);
        return h.handle(c);
    }

    /**
     * Support method for creating the right handler
     * @param request
     * @return Handler
     */
    private Handler getHandler(Request request) {
        return resolver.get(request.getClass().getCanonicalName()).create();
    }
}
