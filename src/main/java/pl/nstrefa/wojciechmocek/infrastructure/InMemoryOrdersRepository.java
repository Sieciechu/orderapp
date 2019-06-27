package pl.nstrefa.wojciechmocek.infrastructure;

import pl.nstrefa.wojciechmocek.domain.Order;
import pl.nstrefa.wojciechmocek.domain.OrdersRepository;

import java.util.LinkedHashSet;
import java.util.Set;

public class InMemoryOrdersRepository implements OrdersRepository {

    private Set<Order> orders = new LinkedHashSet<>();

    @Override
    public void store(Order order) {
        orders.add(order);
    }
}
