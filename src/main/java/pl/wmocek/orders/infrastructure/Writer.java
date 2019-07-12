package pl.wmocek.orders.infrastructure;

import pl.wmocek.orders.application.report.Report;

import java.io.IOException;

public interface Writer {
    void write(Report r) throws IOException;
}
