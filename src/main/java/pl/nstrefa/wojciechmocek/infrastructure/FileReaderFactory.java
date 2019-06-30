package pl.nstrefa.wojciechmocek.infrastructure;

public interface FileReaderFactory {
    public Reader create(String fileFullPath) throws Exception;
}
