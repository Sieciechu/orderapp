package pl.nstrefa.wojciechmocek.domain;

import java.util.Set;

public interface OrdersRepository {
    void store(Order order) throws OrderAlreadyExistsException;

    int countAllOrders();

    int countOrdersForCustomer(ClientId clientId);

    double sumPriceOfAllOrders();

    double sumPriceOfOrdersForCustomer(ClientId clientId);

    Set<Order> getAll();

    Set<Order> getOrdersForCustomer(ClientId clientId);

    double getAveragePriceOfOrder();

    double getAveragePriceOfOrderForCustomer(ClientId clientId);
}
