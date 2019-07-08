package pl.wmocek.orders.infrastructure;

public interface FileReaderFactory {
    public Reader create(String fileFullPath) throws Exception;
}
