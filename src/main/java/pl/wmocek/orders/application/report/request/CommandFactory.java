package pl.wmocek.orders.application.report.request;

@FunctionalInterface
public interface CommandFactory {
    Command create(CommandData data);
}
