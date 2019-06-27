package pl.nstrefa.wojciechmocek.domain;

import pl.nstrefa.wojciechmocek.domain.Order;

public interface OrdersRepository {
    public void store(Order order);
}
