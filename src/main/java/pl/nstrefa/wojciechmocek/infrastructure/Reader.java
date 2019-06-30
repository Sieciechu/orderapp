package pl.nstrefa.wojciechmocek.infrastructure;

import pl.nstrefa.wojciechmocek.domain.Order;

public interface Reader {
    public Order read() throws ReaderException;
    public boolean hasNext();
}
