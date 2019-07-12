package pl.wmocek.orders.application.report.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.report.Report;
import pl.wmocek.orders.application.report.ValueReport;
import pl.wmocek.orders.application.report.request.Request;
import pl.wmocek.orders.domain.CustomerId;
import pl.wmocek.orders.domain.OrdersRepository;

public class AveragePriceOfAllOrdersForCustomer extends OrderedBasedReport {

    public AveragePriceOfAllOrdersForCustomer(@NonNull OrdersRepository ordersRepository) {
        super(ordersRepository);
    }

    @Override
    public Report handle(@NonNull Request request) {

        String customerId = request.getData("customerId");
        var value = ordersRepository.getAveragePriceOfOrderForCustomer(new CustomerId(customerId));

        return new ValueReport(
            String.format("The average price of all orders for customer '%s'", customerId),
            String.format("%.2f", value)
        );
    }
}
