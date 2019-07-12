package pl.wmocek.orders.application.report.handlers;

import lombok.NonNull;
import pl.wmocek.orders.application.report.Report;
import pl.wmocek.orders.application.report.request.Request;

/**
 * Handler handles the given report Request and returns the report
 */
public interface Handler {
    Report handle(@NonNull Request request) throws Exception;
}
