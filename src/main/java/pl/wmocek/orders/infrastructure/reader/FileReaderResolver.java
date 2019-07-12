package pl.wmocek.orders.infrastructure.reader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * FileReaderResolver class uses to determine which <b>File Reader</b> should be created
 */
public class FileReaderResolver {

    /**
     * Map containing file extension as key and FileReaderFactory
     */
    private Map<String, FileReaderFactory> fileReaderFactories = new HashMap<>(2);

    /**
     * The default constructor. For simplicity it has hardcoded default dependencies
     */
    public FileReaderResolver() {
        fileReaderFactories.put("csv", new CsvFileReaderFactory());
        fileReaderFactories.put("xml", new XmlFileReaderFactory());
    }

    /**
     * Constructor which allowes to pass own rules of resolving Reader
     * @param factories
     */
    FileReaderResolver(Map<String, FileReaderFactory> factories) {
        fileReaderFactories = new HashMap<>(factories);
    }

    /**
     * From the fileFullPath extracts the file extension, with this key searches for the defined factory
     *  and creates a Reader
     * @param fileFullPath
     * @return Reader
     * @throws FileNotFoundException
     * @throws ReaderException When there is no reader defined for given file type
     * @throws Exception
     */
    public Reader createReader(String fileFullPath) throws FileNotFoundException, Exception {
        String fileExtension = fileFullPath.substring(fileFullPath.length() - 3).toLowerCase();
        var factory = fileReaderFactories.get(fileExtension);
        if (null == factory) {
            throw new ReaderException("There is no reader defined for given file type '" + fileExtension + "'");
        }
        return factory.create(fileFullPath);
    }
}
