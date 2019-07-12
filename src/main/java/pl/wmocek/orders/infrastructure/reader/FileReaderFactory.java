package pl.wmocek.orders.infrastructure.reader;

/**
 * Interface for creation of a Reader which must read from files
 */
public interface FileReaderFactory {
    public Reader create(String fileFullPath) throws Exception;
}
