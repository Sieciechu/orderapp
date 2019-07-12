package pl.wmocek.orders.application.report;

import pl.wmocek.orders.application.report.request.Request;

/**
 * Controller translates the input to Report Request
 */
public interface Controller {
    Request getRequest();
}
