package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.application.command.ListAllOrdersForCustomerCSVReport;
import pl.wmocek.orders.application.command.handlers.Handler;
import pl.wmocek.orders.application.command.handlers.HandlerFactory;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.OrderCSVFormatter;
import pl.wmocek.orders.io.CSVWriterFactory;

public class ListAllOrdersForCustomerCSVReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public ListAllOrdersForCustomerCSVReportFactory(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Handler create() {
        return new ListAllOrdersForCustomerCSVReport(
            new CSVWriterFactory().create(),
            ordersRepository,
            new OrderCSVFormatter()
        );
    }

}
