package pl.wmocek.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Factory to create Writer to a file
 */
public class FileWriterFactory {

    private String fileName;

    /**
     * @param fileName Just a filename ended with *.csv, example: report1.csv
     */
    public FileWriterFactory(String fileName) {
        this.fileName = fileName;
    }

    public Writer create() throws Exception {
        Path path = FileSystems.getDefault().getPath("/home/wojciech/Projects/orderapp2/src/main/resources/" + fileName);
        try {
            return new BasicWriter(java.nio.file.Files.newBufferedWriter(path));
        } catch (IOException e) {
            throw new Exception("Could not create Writer", e);
        }
    }
}
