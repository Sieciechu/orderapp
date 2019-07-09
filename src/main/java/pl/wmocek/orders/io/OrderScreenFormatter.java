package pl.wmocek.orders.io;

import lombok.NonNull;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.domain.Product;

public class OrderScreenFormatter implements OrderStringer {

    @Override
    public String[] toString(@NonNull Order[] buff) {
        String[] stringedOrders = new String[buff.length];

        for (int i = 0; i < buff.length; i++) {

            if (null == buff[i]) {
                continue;
            }

            var sb = new StringBuilder();
            sb.append(String.format("Request id: %s | Customer '%s' ordered products:\n",
                buff[i].getCustomerId(), buff[i].getRequestId()
            ));

            for (Product p : buff[i].getProducts()) {
                sb.append(String.format("\t- %s,\t%d * %.2f = %.2f\n",
                    p.getName(), p.getQuantity(), p.getUnitPrice(), p.getTotalPrice())
                );
            }
            sb.append("\n");
            stringedOrders[i] = sb.toString();
        }

        return stringedOrders;
    }
}
