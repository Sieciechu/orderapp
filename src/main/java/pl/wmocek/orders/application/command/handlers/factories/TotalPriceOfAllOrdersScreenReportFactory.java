package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.application.command.handlers.Handler;
import pl.wmocek.orders.application.command.handlers.HandlerFactory;
import pl.wmocek.orders.application.command.handlers.TotalPriceOfAllOrdersScreenReport;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.ScreenWriterFactory;

public class TotalPriceOfAllOrdersScreenReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public TotalPriceOfAllOrdersScreenReportFactory(OrdersRepository r) {
        ordersRepository = r;
    }

    @Override
    public Handler create() {
        return new TotalPriceOfAllOrdersScreenReport(
            new ScreenWriterFactory().create(),
            ordersRepository
        );
    }

}
