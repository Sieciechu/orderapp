package pl.wmocek.orders.io.writer;

import java.io.IOException;

/**
 * Writer is the interface that wraps the basic Write method
 */
public interface Writer {

    /**
     * Write writes chunks of strings from buff to the underlying data stream.
     * Write must not modify the slice data, even temporarily.
     * @param in Chunks of strings
     * @return It returns the number of elements written from buff (0 <= n <= buff.length)
     * @throws IOException if thrown on error
     */
    int write(String in) throws IOException;
}
