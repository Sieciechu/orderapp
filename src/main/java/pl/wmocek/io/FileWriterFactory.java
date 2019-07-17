package pl.wmocek.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Factory to create Writer to a file
 */
public class FileWriterFactory {

    private String fileFullPath;

    /**
     * @param fileFullPath File path to which the content will be written.
     *  Can be absolute, example: /tmp/dir/report1.csv
     *  Can be relative to project root, example: src/main/resources/report1.csv
     */
    public FileWriterFactory(String fileFullPath) {
        this.fileFullPath = fileFullPath;
    }

    public Writer create() throws Exception {
        Path path = FileSystems.getDefault().getPath(fileFullPath);
        try {
            return new BasicWriter(java.nio.file.Files.newBufferedWriter(path));
        } catch (IOException e) {
            throw new Exception("Could not create Writer", e);
        }
    }
}
