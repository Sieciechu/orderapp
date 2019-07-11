package pl.wmocek.orders.application;

import pl.wmocek.orders.application.report.request.Command;

public interface CommandBus {
    void send(Command c) throws Exception;
}
