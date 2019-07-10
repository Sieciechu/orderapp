package pl.wmocek.orders.application.command.handlers;

import pl.wmocek.orders.application.command.Handler;
import pl.wmocek.orders.application.command.HandlerFactory;
import pl.wmocek.orders.io.OrderReader;
import pl.wmocek.orders.io.OrderScreenFormatter;
import pl.wmocek.orders.io.ScreenWriterFactory;

public class ListAllOrdersScreenReportHandlerFactory implements HandlerFactory {

    private final OrderReader orderReader;

    public ListAllOrdersScreenReportHandlerFactory(OrderReader orderReader) {
        this.orderReader = orderReader;
    }

    @Override
    public Handler create() {
        return new ListAllOrdersScreenReportHandler(
            new ScreenWriterFactory().create(),
            orderReader,
            new OrderScreenFormatter()
        );
    }

}
