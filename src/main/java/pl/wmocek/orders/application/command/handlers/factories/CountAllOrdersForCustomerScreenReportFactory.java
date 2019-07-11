package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.application.command.handlers.CountAllOrdersForCustomerScreenReport;
import pl.wmocek.orders.application.command.handlers.Handler;
import pl.wmocek.orders.application.command.handlers.HandlerFactory;
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
