package pl.nstrefa.wojciechmocek.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.nstrefa.wojciechmocek.domain.Order;

import java.io.IOException;

class CsvReaderTest {
    @Test
    void whenRowHasAllDataThenNewOrderIsRead() throws IOException {

        // given
        com.opencsv.CSVReader reader = Mockito.mock(com.opencsv.CSVReader.class);
        Mockito.when(reader.readNext()).thenReturn(new String[]{"1", "2", "bread", "3", "1.11"});

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
    void whenRowIsEmptyThenNewOrderIsNotRead() throws IOException {

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