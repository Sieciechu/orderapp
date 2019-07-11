package pl.wmocek.orders.application.report.handlers.factories;

import pl.wmocek.orders.application.report.handlers.AveragePriceOfAllOrdersScreenReport;
import pl.wmocek.orders.application.report.handlers.Handler;
import pl.wmocek.orders.application.report.handlers.HandlerFactory;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.ScreenWriterFactory;

public class AveragePriceOfAllOrdersScreenReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public AveragePriceOfAllOrdersScreenReportFactory(OrdersRepository r) {
        ordersRepository = r;
    }

    @Override
    public Handler create() {
        return new AveragePriceOfAllOrdersScreenReport(
            new ScreenWriterFactory().create(),
            ordersRepository
        );
    }

}
