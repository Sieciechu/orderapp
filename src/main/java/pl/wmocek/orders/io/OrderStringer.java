package pl.wmocek.orders.io;

import lombok.NonNull;
import pl.wmocek.orders.domain.Order;

/**
 * OrderStringer is the interface to map chunk of orders to strings
 */
public interface OrderStringer {
    /**
     * @param buff Array of Orders
     * @return Array of strings. Array length should equal the buff length
     */
    String[] toString(@NonNull Order[] buff);
}
