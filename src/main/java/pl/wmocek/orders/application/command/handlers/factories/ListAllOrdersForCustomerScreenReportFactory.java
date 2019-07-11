package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.application.command.handlers.Handler;
import pl.wmocek.orders.application.command.handlers.HandlerFactory;
import pl.wmocek.orders.application.command.handlers.ListAllOrdersForCustomerScreenReport;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.OrderScreenFormatter;
import pl.wmocek.orders.io.ScreenWriterFactory;

public class ListAllOrdersForCustomerScreenReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public ListAllOrdersForCustomerScreenReportFactory(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Handler create() {
        return new ListAllOrdersForCustomerScreenReport(
            new ScreenWriterFactory().create(),
            ordersRepository,
            new OrderScreenFormatter()
        );
    }

}
