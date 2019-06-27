package pl.nstrefa.wojciechmocek.infrastructure;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReaderResolver {

    private Map<String, FileReaderFactory> fileReaderFactories = new HashMap<>(2);

    public FileReaderResolver(){
        fileReaderFactories.put("csv", new CsvFileReaderFactory());
    }

    FileReaderResolver(Map<String, FileReaderFactory> factories) {
        fileReaderFactories = new HashMap<>(factories);
    }

    public Reader createReader(String fileFullPath) throws IOException {
        String fileExtension = fileFullPath.substring(fileFullPath.length() - 3).toLowerCase();
        var factory = fileReaderFactories.get(fileExtension);
        if (null == factory) {
            return null;
        }
        return factory.create(fileFullPath);
    }
}
