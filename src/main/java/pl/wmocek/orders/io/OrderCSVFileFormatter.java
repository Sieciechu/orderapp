package pl.wmocek.orders.io;

import lombok.NonNull;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.domain.Product;

class OrderCSVFileFormatter implements OrderStringer {

    private String separator;

    public OrderCSVFileFormatter(String separator) {
        this.separator = separator;
    }

    @Override
    public String[] toString(@NonNull Order[] buff) {
        String[] stringedOrders = new String[buff.length];

        for (int i = 0; i < buff.length; i++) {

            if (null == buff[i]) {
                continue;
            }

            var sb = new StringBuilder();

            for (Product p : buff[i].getProducts()) {
                sb.append(String.join(
                    separator,
                    buff[i].getCustomerId().toString(),
                    buff[i].getRequestId().toString(),
                    p.getName(),
                    String.valueOf(p.getQuantity()),
                    String.valueOf(p.getUnitPrice()),
                    String.valueOf(p.getTotalPrice())
                ));
                sb.append("\n");
            }

            stringedOrders[i] = sb.toString();
        }

        return stringedOrders;

    }
}
