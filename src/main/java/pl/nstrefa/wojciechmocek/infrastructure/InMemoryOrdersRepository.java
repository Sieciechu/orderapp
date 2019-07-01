package pl.nstrefa.wojciechmocek.infrastructure;

import lombok.NonNull;
import pl.nstrefa.wojciechmocek.domain.ClientId;
import pl.nstrefa.wojciechmocek.domain.Order;
import pl.nstrefa.wojciechmocek.domain.OrderAlreadyExistsException;
import pl.nstrefa.wojciechmocek.domain.OrdersRepository;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryOrdersRepository implements OrdersRepository {

    private Set<Order> orders = new LinkedHashSet<>();

    @Override
    public void store(Order order) throws OrderAlreadyExistsException {
        boolean orderExists = orders.stream()
            .filter(o -> o.getClientId().equals(order.getClientId()) && o.getRequestId() == order.getRequestId())
            .findFirst().isPresent();

        if (orderExists) {
            throw new OrderAlreadyExistsException(order.getClientId(), order.getRequestId());
        }

        orders.add(order);
    }

    @Override
    public int countAllOrders() {
        return (int) orders.stream()
            .mapToLong(Order::getRequestId)
            .count();
    }

    @Override
    public int countOrdersForCustomer(@NonNull ClientId clientId) {
        return (int) orders.stream()
            .filter(order -> order.getClientId().equals(clientId))
            .mapToLong(Order::getRequestId)
            .count();
    }

    @Override
    public double sumPriceofAllOrders() {
        return orders.stream().mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    public double sumPriceOfOrdersForCustomer(@NonNull ClientId clientId) {
        return orders.stream()
            .filter(order -> order.getClientId().equals(clientId))
            .mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    public Set<Order> getAll() {
        return Collections.unmodifiableSet(orders);
    }

    @Override
    public Set<Order> getOrdersForCustomer(@NonNull ClientId clientId) {
        return orders.stream()
            .filter((Order o) -> o.getClientId().equals(clientId))
            .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public double getAveragePriceOfOrder() {
        return orders.stream().collect(Collectors.averagingDouble(Order::getTotalPrice));
    }

    @Override
    public double getAveragePriceOfOrderForCustomer(@NonNull ClientId clientId) {
        return orders.stream()
            .filter(order -> order.getClientId().equals(clientId))
            .collect(Collectors.averagingDouble(Order::getTotalPrice));
    }
}
