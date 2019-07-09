package pl.wmocek.orders.io;

import pl.wmocek.orders.domain.Order;

import java.io.IOException;

/**
 * OrderReader is the interface to read orders from underlying data stream in chunks.
 */
public interface OrderReader {

    /**
     * EOT (End Of Transmission) constant to indicate when the end of read. See read() documentation
     */
    int EOT = -1;

    /**
     * Read reads orders up to buff.length and returns the number of orders read (0 <= Number of orders read <= buff.length)
     * When read encounters an end of stream condition after successfully reading n > 0 bytes,
     *      it returns the number of bytes read.
     *  When read encounters an end of stream condition after successfully reading n = 0 bytes,
     *      it returns the EOT (End Of Transmission)
     */
    int read(Order[] buff) throws IOException;
}
