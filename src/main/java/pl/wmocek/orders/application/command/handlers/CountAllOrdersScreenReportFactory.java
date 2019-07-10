package pl.wmocek.orders.application.command.handlers;

import pl.wmocek.orders.application.command.Handler;
import pl.wmocek.orders.application.command.HandlerFactory;
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
