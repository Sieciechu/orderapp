package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.CSVWriterFactory;

public class TotalPriceOfAllOrdersForCustomerCSVReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public TotalPriceOfAllOrdersForCustomerCSVReportFactory(OrdersRepository r) {
        ordersRepository = r;
    }

    @Override
    public Handler create() {
        return new TotalPriceOfAllOrdersForCustomerCSVReport(
            new CSVWriterFactory().create(),
            ordersRepository
        );
    }

}
