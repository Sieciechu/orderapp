package pl.nstrefa.wojciechmocek.infrastructure;

import com.opencsv.CSVReader;
import pl.nstrefa.wojciechmocek.domain.Order;

import java.io.IOException;

class CsvReader implements Reader {

    private final CSVReader csvReader;

    CsvReader(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    public Order read() throws IOException {
        String[] row = csvReader.readNext();

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
}
