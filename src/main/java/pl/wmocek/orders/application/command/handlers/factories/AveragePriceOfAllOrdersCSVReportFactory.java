package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.CSVWriterFactory;

public class AveragePriceOfAllOrdersCSVReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public AveragePriceOfAllOrdersCSVReportFactory(OrdersRepository r) {
        ordersRepository = r;
    }

    @Override
    public Handler create() {
        return new AveragePriceOfAllOrdersCSVReport(
            new CSVWriterFactory().create(),
            ordersRepository
        );
    }

}
