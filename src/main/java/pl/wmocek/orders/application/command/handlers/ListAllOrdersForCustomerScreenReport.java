package pl.wmocek.orders.application.command.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.command.Command;
import pl.wmocek.orders.application.command.Handler;
import pl.wmocek.orders.domain.CustomerId;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.io.OrderStringer;
import pl.wmocek.orders.io.Writer;

import java.io.IOException;

class ListAllOrdersForCustomerScreenReport implements Handler {
    private final Writer writer;
    private final OrdersRepository ordersRepository;
    private final OrderStringer stringer;

    public ListAllOrdersForCustomerScreenReport(
        @NonNull Writer writer,
        @NonNull OrdersRepository ordersRepository,
        @NonNull OrderStringer stringer
    ) {
        this.writer = writer;
        this.ordersRepository = ordersRepository;
        this.stringer = stringer;
    }

    @Override
    public void handle(Command command) {

        String customerId = command.getData("customerId");

        Order[] orders = ordersRepository.getOrdersForCustomer(new CustomerId(customerId)).toArray(new Order[0]);

        try {
            writer.write(new String[]{String.format("All orders for the customer '%s':", customerId)});

            String[] stringedOrders = stringer.toString(orders);
            writer.write(stringedOrders);


        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
