package pl.wmocek.orders.infrastructure;

import pl.wmocek.orders.domain.Order;

public interface Reader {
    public Order read() throws ReaderException;
    public boolean hasNext();
}
