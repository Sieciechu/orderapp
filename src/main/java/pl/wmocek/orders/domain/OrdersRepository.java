package pl.wmocek.orders.domain;

import java.util.List;
import java.util.Map;

public interface OrdersRepository {
    void store(Order order) throws OrderAlreadyExistsException;

    void add(Order order);

    int countAllOrders();

    int countOrdersForCustomer(CustomerId customerId);

    double sumPriceOfAllOrders();

    double sumPriceOfOrdersForCustomer(CustomerId customerId);

    Map<RequestId, Order> getAll();

    Map<RequestId, Order> getOrdersForCustomer(CustomerId customerId);

    double getAveragePriceOfOrder();

    double getAveragePriceOfOrderForCustomer(CustomerId customerId);

    List<CustomerId> getDistinctCustomers();
}
