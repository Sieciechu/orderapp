package pl.wmocek.orders.application;

import pl.wmocek.orders.application.command.Command;

public interface Controller {
    Command getCommand();
}
