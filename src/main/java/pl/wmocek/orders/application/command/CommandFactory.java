package pl.wmocek.orders.application.command;

@FunctionalInterface
public interface CommandFactory {
    Command create(CommandData data);
}
