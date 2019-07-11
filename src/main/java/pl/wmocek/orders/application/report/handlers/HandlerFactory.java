package pl.wmocek.orders.application.report.handlers;

@FunctionalInterface
public interface HandlerFactory {
    Handler create();
}
