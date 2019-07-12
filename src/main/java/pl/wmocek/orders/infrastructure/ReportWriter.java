package pl.wmocek.orders.infrastructure;

import pl.wmocek.orders.application.report.Report;
import pl.wmocek.orders.application.report.ReportStringer;

import java.io.IOException;

public class ReportWriter implements Writer {

    private pl.wmocek.writer.Writer writer;
    private ReportStringer reportStringer;

    public ReportWriter(pl.wmocek.writer.Writer writer, ReportStringer reportStringer) {
        this.writer = writer;
        this.reportStringer = reportStringer;
    }

    @Override
    public void write(Report r) throws IOException {

        String stringedReport = reportStringer.toString(r);

        writer.write(stringedReport + "\n");
    }
}
