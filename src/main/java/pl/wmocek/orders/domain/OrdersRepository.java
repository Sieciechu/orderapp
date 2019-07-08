package pl.wmocek.orders.domain;

import java.util.List;
import java.util.Map;

public interface OrdersRepository {
    void store(Order order) throws OrderAlreadyExistsException;

    void add(Order order);

    int countAllOrders();

    int countOrdersForCustomer(ClientId clientId);

    double sumPriceOfAllOrders();

    double sumPriceOfOrdersForCustomer(ClientId clientId);

    Map<RequestId, Order> getAll();

    Map<RequestId, Order> getOrdersForCustomer(ClientId clientId);

    double getAveragePriceOfOrder();

    double getAveragePriceOfOrderForCustomer(ClientId clientId);

    List<ClientId> getDistinctClients();
}
