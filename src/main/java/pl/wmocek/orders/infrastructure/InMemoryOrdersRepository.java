package pl.wmocek.orders.infrastructure;

import lombok.NonNull;
import pl.wmocek.orders.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryOrdersRepository implements OrdersRepository {

    private Map<RequestId, Order> orders = new HashMap<>();

    @Override
    public void store(@NonNull Order order) throws OrderAlreadyExistsException {

        if (orders.containsKey(order.getRequestId())) {
            throw new OrderAlreadyExistsException(order.getCustomerId(), order.getRequestId());
        }

        orders.put(order.getRequestId(), order);
    }

    @Override
    public void add(@NonNull Order order) {
        orders.merge(order.getRequestId(), order, (existingOrder, addingOrder) -> {
            existingOrder.addProducts(addingOrder.getProducts());
            return existingOrder;
        });
    }

    @Override
    public int countAllOrders() {
        return orders.size();
    }

    @Override
    public int countOrdersForCustomer(@NonNull CustomerId customerId) {
        return (int) orders.values().stream()
            .filter(order -> order.getCustomerId().equals(customerId))
            .count();
    }

    @Override
    public double sumPriceOfAllOrders() {
        return orders.values().stream().mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    public double sumPriceOfOrdersForCustomer(@NonNull CustomerId customerId) {
        return orders.values().stream()
            .filter(order -> order.getCustomerId().equals(customerId))
            .mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    public List<Order> getAll() {
        return orders.values().stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Order> getOrdersForCustomer(@NonNull CustomerId customerId) {
        return orders.values().stream()
            .filter(order -> order.getCustomerId().equals(customerId))
            .collect(Collectors.toUnmodifiableList());

    }

    @Override
    public double getAveragePriceOfOrder() {
        return orders.values().stream().collect(Collectors.averagingDouble(Order::getTotalPrice));
    }

    @Override
    public double getAveragePriceOfOrderForCustomer(@NonNull CustomerId customerId) {
        return orders.values().stream()
            .filter(order -> order.getCustomerId().equals(customerId))
            .collect(Collectors.averagingDouble(Order::getTotalPrice));
    }

    @Override
    public List<CustomerId> getDistinctCustomers() {
        return orders.values().stream()
            .map(Order::getCustomerId)
            .distinct().collect(Collectors.toList());
    }
}
