package pl.wmocek.orders.domain;

import java.util.List;

public interface DistinctCustomersRepository {
    List<CustomerId> getDistinctCustomers();
}
