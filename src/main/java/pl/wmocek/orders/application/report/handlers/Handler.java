package pl.wmocek.orders.application.report.handlers;

import pl.wmocek.orders.application.report.request.Command;

public interface Handler {
    void handle(Command command);
}
