package pl.wmocek.orders.infrastructure.stringer;

import pl.wmocek.orders.application.report.Report;
import pl.wmocek.orders.application.report.ReportStringer;
import pl.wmocek.orders.application.report.ValueReport;

public class ValueReportCSVStringer implements ReportStringer {

    @Override
    public String toString(Report r) {
        ValueReport report = (ValueReport)r;
       
        return report.getTitle() + "\n" + report.getValue();
    }
}
