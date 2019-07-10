package pl.wmocek.orders.application.command.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.command.Command;
import pl.wmocek.orders.application.command.Handler;
import pl.wmocek.orders.domain.CustomerId;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.Writer;

import java.io.IOException;

class AveragePriceOfAllOrdersForCustomerScreenReport implements Handler {
    private Writer writer;
    private OrdersRepository repository;

    public AveragePriceOfAllOrdersForCustomerScreenReport(
        @NonNull Writer writer,
        @NonNull OrdersRepository repository
    ) {
        this.writer = writer;
        this.repository = repository;
    }

    @Override
    public void handle(Command command) throws Exception {

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
