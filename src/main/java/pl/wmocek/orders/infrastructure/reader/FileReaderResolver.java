package pl.wmocek.orders.infrastructure.reader;

import pl.wmocek.orders.infrastructure.ReaderException;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class FileReaderResolver {

    private Map<String, FileReaderFactory> fileReaderFactories = new HashMap<>(2);

    public FileReaderResolver() {
        fileReaderFactories.put("csv", new CsvFileReaderFactory());
        fileReaderFactories.put("xml", new XmlFileReaderFactory());
    }

    FileReaderResolver(Map<String, FileReaderFactory> factories) {
        fileReaderFactories = new HashMap<>(factories);
    }

    public Reader createReader(String fileFullPath) throws FileNotFoundException, Exception {
        String fileExtension = fileFullPath.substring(fileFullPath.length() - 3).toLowerCase();
        var factory = fileReaderFactories.get(fileExtension);
        if (null == factory) {
            throw new ReaderException("There is no reader defined for given file type '" + fileExtension + "'");
        }
        return factory.create(fileFullPath);
    }
}
