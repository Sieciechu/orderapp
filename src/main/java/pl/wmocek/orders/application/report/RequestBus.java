package pl.wmocek.orders.application.report;

import pl.wmocek.orders.application.report.request.Request;

/**
 * RequestBus sends the request to the request bus, to be processed by request handler and returns the result
 */
public interface RequestBus {
    Report send(Request c) throws Exception;
}
