package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.CSVWriterFactory;

public class CountAllOrdersCSVReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public CountAllOrdersCSVReportFactory(OrdersRepository r) {
        ordersRepository = r;
    }

    @Override
    public Handler create() {
        return new CountAllOrdersCSVReport(
            new CSVWriterFactory().create(),
            ordersRepository
        );
    }

}
