package pl.nstrefa.wojciechmocek.infrastructure;

import com.opencsv.CSVReader;
import pl.nstrefa.wojciechmocek.domain.Order;

import java.io.IOException;
import java.util.Iterator;

class CsvReader implements Reader {

    private final Iterator<String[]> iterator;
    private final CSVReader reader;

    CsvReader(CSVReader csvReader) {
        this.reader = csvReader;
        this.iterator = csvReader.iterator();
    }

    public Order read() {

        String[] row = iterator.next();

        if(null == row) {
            return null;
        }

        return new Order(
            row[0],
            Long.parseLong(row[1]),
            row[2],
            Integer.parseInt(row[3]),
            Double.parseDouble(row[4])
        );
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}
