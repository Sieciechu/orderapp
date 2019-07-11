package pl.wmocek.orders.application.report.handlers.factories;

import pl.wmocek.orders.application.report.handlers.Handler;
import pl.wmocek.orders.application.report.handlers.HandlerFactory;
import pl.wmocek.orders.application.report.handlers.ListAllOrdersForCustomerScreenReport;
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
