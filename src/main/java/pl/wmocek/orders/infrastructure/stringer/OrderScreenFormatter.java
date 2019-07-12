package pl.wmocek.orders.infrastructure.stringer;

import lombok.NonNull;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.domain.OrderStringer;
import pl.wmocek.orders.domain.Product;

import java.util.List;

public class OrderScreenFormatter implements OrderStringer {

    @Override
    public String toString(@NonNull List<Order> orderList) {

        var sb = new StringBuilder();

        orderList.forEach(order -> {
            sb.append(String.format(
                "Request id: %s | Customer '%s' ordered products:\n\n",
                order.getRequestId(), order.getCustomerId()
            ));

            for (Product p : order.getProducts()) {
                sb.append(String.format("\t- %s,\t%d * %.2f = %.2f\n",
                    p.getName(), p.getQuantity(), p.getUnitPrice(), p.getTotalPrice())
                );
            }
            sb.append("\n");
            sb.append(String.format("Total price: %.2f\n", order.getTotalPrice()));
            sb.append("--------------------------------------------------\n");
            sb.append("\n");
        });

        return sb.toString();
    }
}
