package pl.wmocek.orders.application.command;

import java.util.HashMap;
import java.util.Map;

public abstract class Base implements Command {
    @Override
    public Map<String, String> getData() {
        return new HashMap<>();
    }
}
