package pl.wmocek.orders.infrastructure.stringer;

import pl.wmocek.orders.application.report.ListReport;
import pl.wmocek.orders.application.report.Report;
import pl.wmocek.orders.application.report.ReportStringer;

public class ListReportScreenStringer implements ReportStringer {

    private OrderScreenFormatter orderStringer;

    public ListReportScreenStringer(OrderScreenFormatter orderStringer) {
        this.orderStringer = orderStringer;
    }

    @Override
    public String toString(Report r) {
        ListReport report = (ListReport)r;

        return report.getTitle() + ":\n" + orderStringer.toString(report.getOrders());
    }
}
