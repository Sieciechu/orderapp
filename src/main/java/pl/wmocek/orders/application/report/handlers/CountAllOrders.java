package pl.wmocek.orders.application.report.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.report.Report;
import pl.wmocek.orders.application.report.ValueReport;
import pl.wmocek.orders.application.report.request.Request;
import pl.wmocek.orders.domain.OrdersRepository;

public class CountAllOrders extends OrderedBasedReport {

    public CountAllOrders(@NonNull OrdersRepository ordersRepository) {
        super(ordersRepository);
    }

    @Override
    public Report handle(@NonNull Request request) {

        var value = ordersRepository.countAllOrders();

        return new ValueReport(
            "The number of all orders",
            String.valueOf(value)
        );
    }
}
