package pl.wmocek.orders.infrastructure;

import pl.wmocek.orders.application.report.Report;

import java.io.IOException;

/**
 * Interface for writing reports to underlying write stream
 */
public interface Writer {
    void write(Report r) throws IOException;
}
