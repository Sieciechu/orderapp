package pl.wmocek.orders.application.report.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.report.request.Command;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.Writer;

import java.io.IOException;

class CountAllOrdersScreenReport implements Handler {
    private final Writer writer;
    private final OrdersRepository repository;

    public CountAllOrdersScreenReport(
        @NonNull Writer writer,
        @NonNull OrdersRepository repository
    ) {
        this.writer = writer;
        this.repository = repository;
    }

    @Override
    public void handle(Command command) {

        try {
            var value = repository.countAllOrders();
            writer.write(new String[]{"\nThe number of all orders: " + value + "\n"});
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
