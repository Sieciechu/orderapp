package pl.wmocek.orders.application.command.handlers;

import pl.wmocek.orders.application.command.Handler;
import pl.wmocek.orders.application.command.HandlerFactory;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.ScreenWriterFactory;

public class CountAllOrdersForCustomerScreenReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public CountAllOrdersForCustomerScreenReportFactory(OrdersRepository r) {
        ordersRepository = r;
    }

    @Override
    public Handler create() {
        return new CountAllOrdersForCustomerScreenReport(
            new ScreenWriterFactory().create(),
            ordersRepository
        );
    }

}
