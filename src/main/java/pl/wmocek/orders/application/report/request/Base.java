package pl.wmocek.orders.application.report.request;

import lombok.NonNull;

/**
 * Base class for Request, contains commons for all current Report Requests
 */
public abstract class Base implements Request {

    private final CommandData commandData;

    Base() {
        commandData = new CommandData();
    }

    Base(@NonNull CommandData commandData) {
        this.commandData = commandData;
    }

    @Override
    public String getData(@NonNull String key) {
        return commandData.get(key);
    }
}
