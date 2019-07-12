package pl.wmocek.orders.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileWriterFactory {

    private String fileName;

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
