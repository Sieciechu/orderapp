package pl.wmocek.orders.application.report.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.report.ListReport;
import pl.wmocek.orders.application.report.Report;
import pl.wmocek.orders.application.report.request.Request;
import pl.wmocek.orders.domain.CustomerId;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.domain.OrdersRepository;

public class ListAllOrdersForCustomer extends OrderedBasedReport {

    public ListAllOrdersForCustomer(@NonNull OrdersRepository ordersRepository) {
        super(ordersRepository);
    }

    @Override
    public Report handle(@NonNull Request request) {

        String customerId = request.getData("customerId");

        Order[] orders = ordersRepository.getOrdersForCustomer(new CustomerId(customerId)).toArray(new Order[0]);

        return new ListReport(
            String.format("All orders for the customer '%s':", customerId),
            orders
        );
    }
}
