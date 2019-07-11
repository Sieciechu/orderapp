package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.CSVWriterFactory;

public class TotalPriceOfAllOrdersCSVReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public TotalPriceOfAllOrdersCSVReportFactory(OrdersRepository r) {
        ordersRepository = r;
    }

    @Override
    public Handler create() {
        return new TotalPriceOfAllOrdersCSVReport(
            new CSVWriterFactory().create(),
            ordersRepository
        );
    }

}
