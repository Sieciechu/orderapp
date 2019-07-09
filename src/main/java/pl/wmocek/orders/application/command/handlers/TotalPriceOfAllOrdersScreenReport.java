package pl.wmocek.orders.application.command.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.command.Command;
import pl.wmocek.orders.application.command.Handler;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.Writer;

import java.io.IOException;

class TotalPriceOfAllOrdersScreenReport implements Handler {
    private Writer writer;
    private OrdersRepository repository;

    public TotalPriceOfAllOrdersScreenReport(
        @NonNull Writer writer,
        @NonNull OrdersRepository repository
    ) {
        this.writer = writer;
        this.repository = repository;
    }

    @Override
    public void handle(Command command) throws Exception {

        try {
            var value = repository.sumPriceOfAllOrders();
            writer.write(new String[]{String.format("\nThe total price of all orders: %.2f\n", value)});
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
