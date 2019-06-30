package pl.nstrefa.wojciechmocek.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.nstrefa.wojciechmocek.domain.Order;

import java.io.IOException;
import java.util.Iterator;

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
            new Order("1", 2, "bread", 3, 1.11),
            o
        );
    }

    @Test
    void whenRowIsEmptyThenNewOrderIsNotRead() throws ReaderException, IOException {

        // given
        com.opencsv.CSVReader reader = Mockito.mock(com.opencsv.CSVReader.class);
        Mockito.when(reader.readNext()).thenReturn(null);

        CsvReader csvReader = new CsvReader(reader);

        // when
        Order o = csvReader.read();

        // then
        Assertions.assertNull(o);
    }
}