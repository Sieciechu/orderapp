package pl.nstrefa.wojciechmocek.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

class FileReaderResolverTest {

    @Test
    void whenFileTypeIsSupportedThenReaderShouldBeCreated() throws Exception {

        // given
        var csvReader = Mockito.mock(Reader.class);
        var xmlReader = Mockito.mock(Reader.class);
        Assertions.assertNotEquals(csvReader, xmlReader);

        FileReaderFactory f1 = mock(FileReaderFactory.class);
        FileReaderFactory f2 = mock(FileReaderFactory.class);

        Mockito.when(f1.create("file.csv")).thenReturn(csvReader);
        Mockito.when(f2.create("file.xml")).thenReturn(xmlReader);

        Map<String, FileReaderFactory> factories = new HashMap<>();
        factories.put("csv", f1);
        factories.put("xml", f2);

        var fileReaderResolver = new FileReaderResolver(factories);

        // when-then
        Assertions.assertEquals(csvReader, fileReaderResolver.createReader("file.csv"));
        Assertions.assertEquals(xmlReader, fileReaderResolver.createReader("file.xml"));
    }

    @Test
    void whenFileTypeIsNotSupportedThenReaderShouldNotBeCreated() throws Exception {
        // given
        var csvReader = Mockito.mock(Reader.class);
        var xmlReader = Mockito.mock(Reader.class);
        Assertions.assertNotEquals(csvReader, xmlReader);

        FileReaderFactory f1 = mock(FileReaderFactory.class);
        FileReaderFactory f2 = mock(FileReaderFactory.class);

        Mockito.when(f1.create("file.csv")).thenReturn(csvReader);
        Mockito.when(f2.create("file.xml")).thenReturn(xmlReader);

        Map<String, FileReaderFactory> factories = new HashMap<>();
        factories.put("csv", f1);
        factories.put("xml", f2);

        var fileReaderResolver = new FileReaderResolver(factories);

        // when-then
        Assertions.assertThrows(
            ReaderException.class,
            () -> fileReaderResolver.createReader("file.zzz")
        );
    }

    @Test
    void whenFileTypeIsSupportedButFileDoesNotExistThenItIsImpossibleToCreateReader() throws Exception {
        // given
        var csvReader = Mockito.mock(Reader.class);
        var xmlReader = Mockito.mock(Reader.class);
        Assertions.assertNotEquals(csvReader, xmlReader);

        FileReaderFactory f1 = mock(FileReaderFactory.class);
        FileReaderFactory f2 = mock(FileReaderFactory.class);

        Mockito.when(f1.create("file.csv")).thenThrow(new IOException("File does not exist"));
        Mockito.when(f2.create("file.xml")).thenReturn(xmlReader);

        Map<String, FileReaderFactory> factories = new HashMap<>();
        factories.put("csv", f1);
        factories.put("xml", f2);

        var fileReaderResolver = new FileReaderResolver(factories);

        // when-then
        Assertions.assertThrows(
            IOException.class,
            () -> {fileReaderResolver.createReader("file.csv");}
        );
    }
}