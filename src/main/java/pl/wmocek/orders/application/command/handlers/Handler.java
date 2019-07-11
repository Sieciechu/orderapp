package pl.wmocek.orders.application.command.handlers;

import pl.wmocek.orders.application.command.Command;

public interface Handler {
    void handle(Command command);
}
