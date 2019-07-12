package pl.wmocek.orders.application.report.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.report.Report;
import pl.wmocek.orders.application.report.ValueReport;
import pl.wmocek.orders.application.report.request.Request;
import pl.wmocek.orders.domain.OrdersRepository;

public class TotalPriceOfAllOrders extends OrderedBasedReport {

    public TotalPriceOfAllOrders(@NonNull OrdersRepository ordersRepository) {
        super(ordersRepository);
    }

    @Override
    public Report handle(@NonNull Request request) {

        var value = ordersRepository.sumPriceOfAllOrders();

        return new ValueReport(
            "The total price of all orders",
            String.format("%.2f", value)
        );
    }
}
