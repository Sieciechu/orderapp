package pl.nstrefa.wojciechmocek.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.nstrefa.wojciechmocek.domain.Order;
import pl.nstrefa.wojciechmocek.domain.Product;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class CsvReaderTest {
    @Test
    void whenRowHasAllDataThenNewOrderIsRead() throws ReaderException, IOException {

        // given
        com.opencsv.CSVReader reader = Mockito.mock(com.opencsv.CSVReader.class);
        Iterator<String[]> iterator = Mockito.mock(Iterator.class);
        Mockito.when(reader.iterator()).thenReturn(iterator);
        Mockito.when(iterator.next()).thenReturn(new String[]{"1", "2", "bread", "3", "1.11"});

        CsvReader csvReader = new CsvReader(reader);

        // when
        Order o = csvReader.read();

        // then
        Assertions.assertEquals(
            Order.create("1", 2, new Product("bread", 3, 1.11)),
            o
        );
    }

    @Test
    void whenThereAreNoRowsThenNewOrderIsNotRead() throws ReaderException, IOException {

        // given
        com.opencsv.CSVReader reader = Mockito.mock(com.opencsv.CSVReader.class);
        Iterator<String[]> iterator = Mockito.mock(Iterator.class);
        Mockito.when(reader.iterator()).thenReturn(iterator);
        Mockito.when(iterator.next()).thenThrow(NoSuchElementException.class);

        CsvReader csvReader = new CsvReader(reader);

        // when-then
        Assertions.assertThrows(
            NoSuchElementException.class,
            () -> csvReader.read()
        );
    }
}