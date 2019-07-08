package pl.nstrefa.wojciechmocek.infrastructure;

import lombok.NonNull;
import pl.nstrefa.wojciechmocek.domain.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryOrdersRepository implements OrdersRepository {

    private Map<RequestId, Order> orders = new HashMap<>();

    @Override
    public void store(@NonNull Order order) throws OrderAlreadyExistsException {

        if (orders.containsKey(order.getRequestId())) {
            throw new OrderAlreadyExistsException(order.getClientId(), order.getRequestId());
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
    public int countOrdersForCustomer(@NonNull ClientId clientId) {
        return (int) orders.values().stream()
            .filter(order -> order.getClientId().equals(clientId))
            .count();
    }

    @Override
    public double sumPriceOfAllOrders() {
        return orders.values().stream().mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    public double sumPriceOfOrdersForCustomer(@NonNull ClientId clientId) {
        return orders.values().stream()
            .filter(order -> order.getClientId().equals(clientId))
            .mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    public Map<RequestId, Order> getAll() {
        return Collections.unmodifiableMap(orders);
    }

    @Override
    public Map<RequestId, Order> getOrdersForCustomer(@NonNull ClientId clientId) {
        return orders.entrySet().stream()
            .filter((o) -> o.getValue().getClientId().equals(clientId))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    @Override
    public double getAveragePriceOfOrder() {
        return orders.values().stream().collect(Collectors.averagingDouble(Order::getTotalPrice));
    }

    @Override
    public double getAveragePriceOfOrderForCustomer(@NonNull ClientId clientId) {
        return orders.values().stream()
            .filter(order -> order.getClientId().equals(clientId))
            .collect(Collectors.averagingDouble(Order::getTotalPrice));
    }

    @Override
    public List<ClientId> getDistinctClients() {
        return orders.values().stream()
            .map(Order::getClientId)
            .distinct().collect(Collectors.toList());
    }
}
