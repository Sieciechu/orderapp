package pl.wmocek.orders.application.command.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.command.Command;
import pl.wmocek.orders.domain.CustomerId;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.Writer;

import java.io.IOException;

class AveragePriceOfAllOrdersForCustomerCSVReport implements Handler {
    private final Writer writer;
    private final OrdersRepository repository;

    AveragePriceOfAllOrdersForCustomerCSVReport(
        @NonNull Writer writer,
        @NonNull OrdersRepository repository
    ) {
        this.writer = writer;
        this.repository = repository;
    }

    @Override
    public void handle(Command command) {

        String customerId = command.getData("customerId");

        var value = repository.sumPriceOfOrdersForCustomer(new CustomerId(customerId));

        String text = String.format("\nThe average price of all orders for customer '%s' : %.2f\n",
            customerId, value);


        try {
            writer.write(new String[]{text});
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
