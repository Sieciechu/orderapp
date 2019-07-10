package pl.wmocek.orders.application.command;

@FunctionalInterface
public interface HandlerFactory {
    Handler create();
}
