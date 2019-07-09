package pl.wmocek.orders.application.command;

import pl.wmocek.orders.io.OrderReader;
import pl.wmocek.orders.io.OrderScreenFormatter;
import pl.wmocek.orders.io.ScreenWriterFactory;

class CreateAllOrdersScreenReportHandlerFactory implements HandlerFactory {

    private OrderReader orderReader;

    public CreateAllOrdersScreenReportHandlerFactory(OrderReader orderReader) {
        this.orderReader = orderReader;
    }

    @Override
    public Handler create() {
        return new CreateAllOrdersScreenReportHandler(
            new ScreenWriterFactory().create(),
            orderReader,
            new OrderScreenFormatter()
        );
    }

}
