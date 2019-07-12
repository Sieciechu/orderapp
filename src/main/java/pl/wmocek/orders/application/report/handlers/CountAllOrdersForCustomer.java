package pl.wmocek.orders.application.report.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.report.Report;
import pl.wmocek.orders.application.report.ValueReport;
import pl.wmocek.orders.application.report.request.Request;
import pl.wmocek.orders.domain.CustomerId;
import pl.wmocek.orders.domain.OrdersRepository;

public class CountAllOrdersForCustomer extends OrderedBasedReport {

    public CountAllOrdersForCustomer(@NonNull OrdersRepository ordersRepository) {
        super(ordersRepository);
    }

    @Override
    public Report handle(@NonNull Request request) {

        String customerId = request.getData("customerId");
        var value = ordersRepository.countOrdersForCustomer(new CustomerId(customerId));

        return new ValueReport(
            String.format("The number of all orders for customer '%s'", customerId),
            String.format("%d", value)
        );
    }
}
