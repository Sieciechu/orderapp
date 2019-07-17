package pl.wmocek.io.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wmocek.io.FileWriterFactory;
import pl.wmocek.io.Writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileWriterFactoryTest {

    @Test
    void whenDirectoryDoesNotExistItShouldBeNotPossibleToCreateWriter() {
        // given
        String notExistingFilePath = "nonExistingDirectory/reportName";

        FileWriterFactory factory = new FileWriterFactory(notExistingFilePath);

        // when-then
        Assertions.assertThrows(
            Exception.class,
            factory::create
        );
    }

    @Test
    void whenDirectoryExistsThenFileShouldBeCreated() throws Exception {
        // given
        Files.createDirectory(Path.of("src/test/java/pl/wmocek/io/integration/test"));
        String existingFilePath = "src/test/java/pl/wmocek/io/integration/test/reportName";

        FileWriterFactory factory = new FileWriterFactory(existingFilePath);

        // when
        Writer writer = factory.create();

        // then
        Path expectedFile = Path.of("src/test/java/pl/wmocek/io/integration/test/reportName");
        Assertions.assertTrue(Files.exists(expectedFile));

        // cleanup
        Files.delete(Path.of("src/test/java/pl/wmocek/io/integration/test/reportName"));
        Files.delete(Path.of("src/test/java/pl/wmocek/io/integration/test"));

    }
}