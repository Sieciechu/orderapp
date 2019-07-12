package pl.wmocek.orders.infrastructure.reader;

import pl.wmocek.orders.domain.Order;

/**
 * The Reader interface
 */
public interface Reader {
    public Order read() throws ReaderException;
    public boolean hasNext();
}
