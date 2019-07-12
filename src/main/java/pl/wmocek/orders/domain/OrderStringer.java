package pl.wmocek.orders.domain;

import lombok.NonNull;

import java.util.List;

/**
 * OrderStringer is the interface to map chunk of orders to strings
 */
public interface OrderStringer {

    String toString(@NonNull List<Order> orderList);
}
