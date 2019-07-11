package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.application.command.handlers.Handler;
import pl.wmocek.orders.application.command.handlers.HandlerFactory;
import pl.wmocek.orders.application.command.handlers.TotalPriceOfAllOrdersForCustomerScreenReport;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.ScreenWriterFactory;

public class TotalPriceOfAllOrdersForCustomerScreenReportFactory implements HandlerFactory {

    private final OrdersRepository ordersRepository;

    public TotalPriceOfAllOrdersForCustomerScreenReportFactory(OrdersRepository r) {
        ordersRepository = r;
    }

    @Override
    public Handler create() {
        return new TotalPriceOfAllOrdersForCustomerScreenReport(
            new ScreenWriterFactory().create(),
            ordersRepository
        );
    }

}
