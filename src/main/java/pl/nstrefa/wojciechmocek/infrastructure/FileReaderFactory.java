package pl.nstrefa.wojciechmocek.infrastructure;

import java.io.IOException;

public interface FileReaderFactory {
    public Reader create(String fileFullPath) throws IOException;
}
