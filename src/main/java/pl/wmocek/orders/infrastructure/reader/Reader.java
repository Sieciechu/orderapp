package pl.wmocek.orders.infrastructure.reader;

import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.infrastructure.ReaderException;

public interface Reader {
    public Order read() throws ReaderException;
    public boolean hasNext();
}
