package pl.wmocek.orders.application.command;

import lombok.NonNull;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.io.OrderReader;
import pl.wmocek.orders.io.OrderStringer;
import pl.wmocek.orders.io.Writer;

import java.io.IOException;

class CreateAllOrdersScreenReportHandler implements Handler {
    private Writer writer;
    private OrderReader reader;
    private OrderStringer stringer;

    public CreateAllOrdersScreenReportHandler(
        @NonNull Writer writer,
        @NonNull OrderReader reader,
        @NonNull OrderStringer stringer
    ) {
        this.writer = writer;
        this.reader = reader;
        this.stringer = stringer;
    }

    @Override
    public void handle(Command command) throws Exception {
        final int buffSize = 3;
        Order[] orders = new Order[buffSize];
        String[] stringedOrders = new String[buffSize];

        try {
            writer.write(new String[]{"All orders:"});

            int n=0;

            while ((n = reader.read(orders)) != OrderReader.EOT) {
                stringedOrders = stringer.toString(orders);
                writer.write(stringedOrders);
            }

        } catch (IOException e) {
            System.err.println("Error occured: " + e.getMessage());
        }
    }
}
