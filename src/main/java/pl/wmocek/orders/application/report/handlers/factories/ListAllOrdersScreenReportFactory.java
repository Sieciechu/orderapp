package pl.wmocek.orders.application.report.handlers.factories;

import pl.wmocek.orders.application.report.handlers.Handler;
import pl.wmocek.orders.application.report.handlers.HandlerFactory;
import pl.wmocek.orders.application.report.handlers.ListAllOrdersScreenReport;
import pl.wmocek.orders.io.OrderReader;
import pl.wmocek.orders.io.OrderScreenFormatter;
import pl.wmocek.orders.io.ScreenWriterFactory;

public class ListAllOrdersScreenReportFactory implements HandlerFactory {

    private final OrderReader orderReader;

    public ListAllOrdersScreenReportFactory(OrderReader orderReader) {
        this.orderReader = orderReader;
    }

    @Override
    public Handler create() {
        return new ListAllOrdersScreenReport(
            new ScreenWriterFactory().create(),
            orderReader,
            new OrderScreenFormatter()
        );
    }

}
