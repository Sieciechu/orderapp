package pl.wmocek.orders.application.report.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.report.request.Command;
import pl.wmocek.orders.domain.CustomerId;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.Writer;

import java.io.IOException;

class AveragePriceOfAllOrdersForCustomerScreenReport implements Handler {
    private final Writer writer;
    private final OrdersRepository repository;

    AveragePriceOfAllOrdersForCustomerScreenReport(
        @NonNull Writer writer,
        @NonNull OrdersRepository repository
    ) {
        this.writer = writer;
        this.repository = repository;
    }

    @Override
    public void handle(Command command) {

        String customerId = command.getData("customerId");

        var value = repository.getAveragePriceOfOrderForCustomer(new CustomerId(customerId));

        String text = String.format("\nThe average price of all orders for customer '%s' : %.2f\n",
            customerId, value);


        try {
            writer.write(new String[]{text});
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
