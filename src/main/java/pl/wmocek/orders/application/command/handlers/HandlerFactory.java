package pl.wmocek.orders.application.command.handlers;

@FunctionalInterface
public interface HandlerFactory {
    Handler create();
}
