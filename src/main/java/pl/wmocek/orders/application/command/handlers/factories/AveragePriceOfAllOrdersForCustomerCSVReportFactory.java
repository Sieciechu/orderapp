package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.CSVWriterFactory;

public class AveragePriceOfAllOrdersForCustomerCSVReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public AveragePriceOfAllOrdersForCustomerCSVReportFactory(OrdersRepository r) {
        ordersRepository = r;
    }

    @Override
    public Handler create() {
        return new AveragePriceOfAllOrdersForCustomerCSVReport(
            new CSVWriterFactory().create(),
            ordersRepository
        );
    }

}
