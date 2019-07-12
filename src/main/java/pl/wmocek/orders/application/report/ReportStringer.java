package pl.wmocek.orders.application.report;

/**
 * ReportStringer interface to convert/map the report to string
 */
public interface ReportStringer {
    String toString(Report r);
}
