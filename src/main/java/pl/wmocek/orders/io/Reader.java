package pl.wmocek.orders.io;

import pl.wmocek.orders.domain.Order;

import java.io.IOException;

/**
 * OrderReader is the interface to read string from underlying data stream in chunks.
 */
public interface Reader {

    String read() throws IOException;
}
