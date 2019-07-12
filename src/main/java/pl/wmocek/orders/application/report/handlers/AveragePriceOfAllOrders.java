package pl.wmocek.orders.application.report.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.report.Report;
import pl.wmocek.orders.application.report.ValueReport;
import pl.wmocek.orders.application.report.request.Request;
import pl.wmocek.orders.domain.OrdersRepository;

public class AveragePriceOfAllOrders extends OrderedBasedReport {

    public AveragePriceOfAllOrders(@NonNull OrdersRepository ordersRepository) {
        super(ordersRepository);
    }

    @Override
    public Report handle(@NonNull Request request) {

        var value = ordersRepository.getAveragePriceOfOrder();

        return new ValueReport(
            "The average price of all orders",
            String.format("%.2f", value)
        );
    }
}
