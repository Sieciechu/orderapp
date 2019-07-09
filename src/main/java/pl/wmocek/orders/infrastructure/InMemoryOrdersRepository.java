package pl.wmocek.orders.infrastructure;

import lombok.NonNull;
import pl.wmocek.orders.domain.*;
import pl.wmocek.orders.io.OrderReader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryOrdersRepository implements OrdersRepository, OrderReader, DistinctCustomersRepository {

    private List<Order> orders = new ArrayList<>();

    private int readPosition = 0;

    @Override
    public void store(@NonNull Order newOrder) throws OrderAlreadyExistsException {

        Optional<Order> order = orders.stream()
            .filter(o -> o.getRequestId().equals(newOrder.getRequestId()))
            .findFirst();


        if (order.isPresent()) {
            throw new OrderAlreadyExistsException(newOrder.getCustomerId(), newOrder.getRequestId());
        }

        orders.add(newOrder);
    }

    @Override
    public void add(@NonNull Order newOrder) {

        orders.stream()
            .filter(o -> o.getRequestId().equals(newOrder.getRequestId()))
            .findFirst()
            .ifPresentOrElse(
                (Order o) -> o.addProducts(newOrder.getProducts()),
                () -> orders.add(newOrder)
            );
    }

    @Override
    public int countAllOrders() {
        return orders.size();
    }

    @Override
    public int countOrdersForCustomer(@NonNull CustomerId customerId) {
        return (int) orders.stream()
            .filter(order -> order.getCustomerId().equals(customerId))
            .count();
    }

    @Override
    public double sumPriceOfAllOrders() {
        return orders.stream().mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    public double sumPriceOfOrdersForCustomer(@NonNull CustomerId customerId) {
        return orders.stream()
            .filter(order -> order.getCustomerId().equals(customerId))
            .mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    public List<Order> getAll() {
        return orders.stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Order> getOrdersForCustomer(@NonNull CustomerId customerId) {
        return orders.stream()
            .filter(order -> order.getCustomerId().equals(customerId))
            .collect(Collectors.toUnmodifiableList());

    }

    @Override
    public double getAveragePriceOfOrder() {
        return orders.stream().collect(Collectors.averagingDouble(Order::getTotalPrice));
    }

    @Override
    public double getAveragePriceOfOrderForCustomer(@NonNull CustomerId customerId) {
        return orders.stream()
            .filter(order -> order.getCustomerId().equals(customerId))
            .collect(Collectors.averagingDouble(Order::getTotalPrice));
    }

    @Override
    public List<CustomerId> getDistinctCustomers() {
        return orders.stream()
            .map(Order::getCustomerId)
            .distinct().collect(Collectors.toList());
    }

    @Override
    public int read(Order[] buff) throws IOException {
        int n = 0;

        for (int i = 0; i < buff.length; i++) {
            buff[i] = null;
        }

        int repoSize = this.orders.size();

        if(readPosition >= repoSize) {
            return OrderReader.EOT;
        }

        for (int i = 0; i < buff.length; ++i) {
            buff[i] = this.orders.get(readPosition);
            ++readPosition;
            ++n;

            if(readPosition >= repoSize) {
                return n;
            }
        }

        return n;
    }
}
