package pl.wmocek.orders.application;

import pl.wmocek.orders.application.command.Command;

public interface CommandBus {
    void send(Command c) throws Exception;
}
