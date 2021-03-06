package pl.wmocek.orders.domain;

import java.util.List;

public interface OrdersRepository {
    void store(Order order) throws OrderAlreadyExistsException;

    void add(Order order);

    int countAllOrders();

    int countOrdersForCustomer(CustomerId customerId);

    double sumPriceOfAllOrders();

    double sumPriceOfOrdersForCustomer(CustomerId customerId);

    List<Order> getAll(int offset, int limit);

    List<Order> getOrdersForCustomer(CustomerId customerId);

    double getAveragePriceOfOrder();

    double getAveragePriceOfOrderForCustomer(CustomerId customerId);
}
