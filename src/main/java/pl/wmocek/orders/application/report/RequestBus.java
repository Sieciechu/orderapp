package pl.wmocek.orders.application.report;

import pl.wmocek.orders.application.report.request.Request;

public interface RequestBus {
    Report send(Request c) throws Exception;
}
