package pl.nstrefa.wojciechmocek.infrastructure;

import com.opencsv.CSVReader;
import pl.nstrefa.wojciechmocek.domain.Order;

import java.util.Iterator;

class CsvReader implements Reader {

    private final Iterator<String[]> iterator;

    CsvReader(CSVReader csvReader) {
        this.iterator = csvReader.iterator();
    }

    public Order read() {

        String[] row = iterator.next();

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
