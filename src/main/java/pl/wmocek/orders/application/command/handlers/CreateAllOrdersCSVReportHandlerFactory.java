package pl.wmocek.orders.application.command.handlers;

import pl.wmocek.orders.application.command.Handler;
import pl.wmocek.orders.application.command.HandlerFactory;
import pl.wmocek.orders.io.OrderCSVFileFormatter;
import pl.wmocek.orders.io.OrderReader;

public class CreateAllOrdersCSVReportHandlerFactory implements HandlerFactory {
    private final OrderReader orderReader;

    private final String separator = ",";

    public CreateAllOrdersCSVReportHandlerFactory(OrderReader orderReader) {
        this.orderReader = orderReader;
    }

    @Override
    public Handler create() {
        return new CreateAllOrdersCSVReportHandler(
            null,
            orderReader,
            new OrderCSVFileFormatter(separator),
            separator
        );
    }
}
