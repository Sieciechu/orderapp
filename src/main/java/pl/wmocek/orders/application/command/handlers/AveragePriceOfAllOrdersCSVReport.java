package pl.wmocek.orders.application.command.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.command.Command;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.Writer;

import java.io.IOException;

class AveragePriceOfAllOrdersCSVReport implements Handler {
    private final Writer writer;
    private final OrdersRepository repository;

    AveragePriceOfAllOrdersCSVReport(
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
            writer.write(new String[]{
                "The average price of all orders",
                String.format("%.2f",value)
            });
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
