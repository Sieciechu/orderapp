package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.CSVWriterFactory;

public class CountAllOrdersForCustomerCSVReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public CountAllOrdersForCustomerCSVReportFactory(OrdersRepository r) {
        ordersRepository = r;
    }

    @Override
    public Handler create() {
        return new CountAllOrdersForCustomerCSVReport(
            new CSVWriterFactory().create(),
            ordersRepository
        );
    }

}
