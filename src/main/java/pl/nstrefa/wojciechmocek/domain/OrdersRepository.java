package pl.nstrefa.wojciechmocek.domain;

import java.util.Set;

public interface OrdersRepository {
    void store(Order order);

    int countAllOrders();

    int countOrdersForCustomer(ClientId clientId);

    double sumPriceofAllOrders();

    double sumPriceOfOrdersForCustomer(ClientId clientId);

    Set<Order> getAll();

    Set<Order> getOrdersForCustomer(ClientId clientId);

    double getAveragePriceOfOrderForCustomer();

    double getAveragePriceOfOrderForCustomer(ClientId clientId);
}
