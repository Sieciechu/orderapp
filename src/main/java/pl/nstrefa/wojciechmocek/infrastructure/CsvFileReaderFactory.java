package pl.nstrefa.wojciechmocek.infrastructure;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class CsvFileReaderFactory implements FileReaderFactory {
    public Reader create(String fileFullPath) throws IOException {
        CSVParser csvParser = new CSVParserBuilder()
            .withSeparator(',')
            .withIgnoreQuotations(true)
            .build();

        CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new FileReader(fileFullPath)))
            .withSkipLines(1)
            .withCSVParser(csvParser)
            .build();

        return new CsvReader(csvReader);
    }
}
