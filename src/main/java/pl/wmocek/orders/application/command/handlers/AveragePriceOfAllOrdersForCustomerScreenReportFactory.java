package pl.wmocek.orders.application.command.handlers;

import pl.wmocek.orders.application.command.Handler;
import pl.wmocek.orders.application.command.HandlerFactory;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.ScreenWriterFactory;

public class AveragePriceOfAllOrdersForCustomerScreenReportFactory implements HandlerFactory {

    private OrdersRepository ordersRepository;

    public AveragePriceOfAllOrdersForCustomerScreenReportFactory(OrdersRepository r) {
        ordersRepository = r;
    }

    @Override
    public Handler create() {
        return new AveragePriceOfAllOrdersForCustomerScreenReport(
            new ScreenWriterFactory().create(),
            ordersRepository
        );
    }

}
