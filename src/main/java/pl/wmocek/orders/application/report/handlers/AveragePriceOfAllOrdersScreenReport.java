package pl.wmocek.orders.application.report.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.report.request.Command;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.Writer;

import java.io.IOException;

class AveragePriceOfAllOrdersScreenReport implements Handler {
    private final Writer writer;
    private final OrdersRepository repository;

    AveragePriceOfAllOrdersScreenReport(
        @NonNull Writer writer,
        @NonNull OrdersRepository repository
    ) {
        this.writer = writer;
        this.repository = repository;
    }

    @Override
    public void handle(Command command) {

        try {
            var value = repository.getAveragePriceOfOrder();
            writer.write(new String[]{String.format("\nThe average price of all orders: %.2f\n", value)});
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
