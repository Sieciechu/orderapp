package pl.wmocek.orders.infrastructure.stringer;

import lombok.NonNull;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.domain.OrderStringer;

import java.util.List;

public class OrderCSVFileFormatter implements OrderStringer {

    private String separator;

    public OrderCSVFileFormatter(String separator) {
        this.separator = separator;
    }

    @Override
    public String toString(@NonNull List<Order> orderList) {

        var sb = new StringBuilder();

        orderList.forEach(order -> {
            order.getProducts().forEach(product -> {
                sb.append(String.join(
                    separator,
                    order.getCustomerId().toString(),
                    order.getRequestId().toString(),
                    product.getName(),
                    String.valueOf(product.getQuantity()),
                    String.valueOf(product.getUnitPrice()),
                    String.valueOf(product.getTotalPrice())
                ));
                sb.append("\n");
            });
        });

        return sb.toString();
    }
}
