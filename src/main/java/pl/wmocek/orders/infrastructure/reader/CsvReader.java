package pl.wmocek.orders.infrastructure.reader;

import com.opencsv.CSVReader;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.domain.Product;

import java.util.Iterator;

/**
 * CsvReader class for creating the Orders from a CSV file
 */
class CsvReader implements Reader {

    private final Iterator<String[]> iterator;

    CsvReader(CSVReader csvReader) {
        this.iterator = csvReader.iterator();
    }

    public Order read() {

        String[] row = iterator.next();

        Product product = new Product(
            row[2],
            Integer.parseInt(row[3]),
            Double.parseDouble(row[4])
        );
        return Order.create(
            row[0],
            Long.parseLong(row[1]),
            product
        );
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}
