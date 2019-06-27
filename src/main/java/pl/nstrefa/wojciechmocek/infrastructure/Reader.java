package pl.nstrefa.wojciechmocek.infrastructure;

import pl.nstrefa.wojciechmocek.domain.Order;

import java.io.IOException;

public interface Reader {
    public Order read() throws IOException;
}
