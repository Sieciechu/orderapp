package pl.wmocek.orders.application.command.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.command.Command;
import pl.wmocek.orders.application.command.Handler;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.io.OrderReader;
import pl.wmocek.orders.io.OrderStringer;
import pl.wmocek.orders.io.Writer;

import java.io.IOException;

class ListAllOrdersScreenReportHandler implements Handler {
    private final Writer writer;
    private final OrderReader reader;
    private final OrderStringer stringer;

    public ListAllOrdersScreenReportHandler(
        @NonNull Writer writer,
        @NonNull OrderReader reader,
        @NonNull OrderStringer stringer
    ) {
        this.writer = writer;
        this.reader = reader;
        this.stringer = stringer;
    }

    @Override
    public void handle(Command command) {
        final int buffSize = 3;
        Order[] orders = new Order[buffSize];

        try {
            writer.write(new String[]{"All orders:"});

            while (reader.read(orders) != OrderReader.EOT) {
                String[] stringedOrders = stringer.toString(orders);
                writer.write(stringedOrders);
            }

        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
