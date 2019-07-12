package pl.wmocek.orders.infrastructure.reader;

public interface FileReaderFactory {
    public Reader create(String fileFullPath) throws Exception;
}
