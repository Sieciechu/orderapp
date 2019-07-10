package pl.wmocek.orders.application.command;

public abstract class Base implements Command {

    private final CommandData commandData;

    Base() {
        commandData = new CommandData();
    }

    Base(CommandData commandData) {
        this.commandData = commandData;
    }

    @Override
    public String getData(String key) {
        return commandData.get(key);
    }
}
