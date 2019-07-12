package pl.wmocek.orders.infrastructure.stringer;

import pl.wmocek.orders.application.report.ListReport;
import pl.wmocek.orders.application.report.Report;
import pl.wmocek.orders.application.report.ReportStringer;

public class ListReportCSVStringer implements ReportStringer {

    private String separator;
    private OrderCSVFileFormatter orderStringer;

    public ListReportCSVStringer(String separator, OrderCSVFileFormatter orderStringer) {
        this.separator = separator;
        this.orderStringer = orderStringer;
    }

    @Override
    public String toString(Report r) {
        ListReport report = (ListReport)r;
        String title = String.join(
            separator,
            "CustomerId",
            "RequestId",
            "ProductName",
            "Quantity",
            "UnitPrice",
            "TotalPrice"
        );

        return title + "\n" + orderStringer.toString(report.getOrders());
    }
}
