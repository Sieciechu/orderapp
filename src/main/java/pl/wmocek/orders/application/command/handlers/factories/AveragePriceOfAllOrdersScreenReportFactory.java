package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.application.command.handlers.AveragePriceOfAllOrdersScreenReport;
import pl.wmocek.orders.application.command.handlers.Handler;
import pl.wmocek.orders.application.command.handlers.HandlerFactory;
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
