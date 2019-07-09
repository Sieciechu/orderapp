package pl.wmocek.orders.application.command.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.command.Command;
import pl.wmocek.orders.application.command.Handler;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.io.OrderReader;
import pl.wmocek.orders.io.OrderStringer;
import pl.wmocek.orders.io.Writer;

import java.io.IOException;

class CreateAllOrdersCSVReportHandler implements Handler {
    private Writer writer;
    private OrderReader reader;
    private OrderStringer stringer;
    private String CSVSeparator;

    public CreateAllOrdersCSVReportHandler(
        @NonNull Writer writer,
        @NonNull OrderReader reader,
        @NonNull OrderStringer stringer,
        @NonNull String CSVSeparator
    ) {
        this.writer = writer;
        this.reader = reader;
        this.stringer = stringer;
        this.CSVSeparator = CSVSeparator;
    }

    @Override
    public void handle(Command command) throws Exception {
        final int buffSize = 3;
        Order[] orders = new Order[buffSize];

        try {
            writer.write(new String[]{String.join(CSVSeparator,
                "customerId", "requestId", "product name",
                "quantity", "unit price", "total price"
            )});

            int n=0;

            while ((n = reader.read(orders)) != OrderReader.EOT) {
                String[] stringedOrders = stringer.toString(orders);
                writer.write(stringedOrders);
            }

        } catch (IOException e) {
            System.err.println("Error occured: " + e.getMessage());
        }
    }
}
