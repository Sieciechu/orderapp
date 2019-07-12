package pl.wmocek.io;

import java.io.IOException;

/**
 * Writer is the interface that wraps the basic Write method
 */
public interface Writer {

    /**
     * Write writes the input string to the underlying data stream.
     * @param in String to be written
     * @return It returns the number of elements written from the input string (0 <= n <= buff.length)
     * @throws IOException if thrown on error
     */
    int write(String in) throws IOException;
}
