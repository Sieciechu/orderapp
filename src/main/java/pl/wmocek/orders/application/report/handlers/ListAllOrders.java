package pl.wmocek.orders.application.report.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.report.ListReport;
import pl.wmocek.orders.application.report.Report;
import pl.wmocek.orders.application.report.request.Request;
import pl.wmocek.orders.domain.OrdersRepository;

public class ListAllOrders extends OrderedBasedReport {

    public ListAllOrders(@NonNull OrdersRepository ordersRepository) {
        super(ordersRepository);
    }

    @Override
    public Report handle(@NonNull Request request) throws Exception {

        int limit = 0;
        int offset = 0;
        try {
            limit = Integer.valueOf(request.getData("limit"));
            offset = Integer.valueOf(request.getData("offset"));
        } catch (NumberFormatException e) {
            throw new Exception("Cannot handle request, offset or limit is missing or have non int values");
        }

        return new ListReport(
            "List of all orders, starting from " + offset + " limit by " + limit,
            ordersRepository.getAll(offset, limit)
        );

    }
}
