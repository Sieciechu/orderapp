package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.application.command.handlers.CountAllOrdersScreenReport;
import pl.wmocek.orders.application.command.handlers.Handler;
import pl.wmocek.orders.application.command.handlers.HandlerFactory;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.ScreenWriterFactory;

public class CountAllOrdersScreenReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public CountAllOrdersScreenReportFactory(OrdersRepository r) {
        ordersRepository = r;
    }

    @Override
    public Handler create() {
        return new CountAllOrdersScreenReport(
            new ScreenWriterFactory().create(),
            ordersRepository
        );
    }

}
