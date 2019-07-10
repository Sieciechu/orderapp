package pl.wmocek.orders.application.command;

public abstract class Base implements Command {

    private CommandData commandData;

    public Base() {
        commandData = new CommandData();
    }

    public Base(CommandData commandData) {
        this.commandData = commandData;
    }

    @Override
    public String getData(String key) {
        return commandData.get(key);
    }
}
