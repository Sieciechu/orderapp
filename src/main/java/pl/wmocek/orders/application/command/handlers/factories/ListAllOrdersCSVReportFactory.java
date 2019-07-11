package pl.wmocek.orders.application.command.handlers.factories;

import pl.wmocek.orders.application.command.handlers.Handler;
import pl.wmocek.orders.application.command.handlers.HandlerFactory;
import pl.wmocek.orders.application.command.handlers.ListAllOrdersCSVReport;
import pl.wmocek.orders.io.OrderReader;
import pl.wmocek.orders.io.OrderCSVFormatter;
import pl.wmocek.orders.io.CSVWriterFactory;

public class ListAllOrdersCSVReportFactory implements HandlerFactory {

    private final OrderReader orderReader;

    public ListAllOrdersCSVReportFactory(OrderReader orderReader) {
        this.orderReader = orderReader;
    }

    @Override
    public Handler create() {
        return new ListAllOrdersCSVReport(
            new CSVWriterFactory().create(),
            orderReader,
            new OrderCSVFormatter()
        );
    }

}
