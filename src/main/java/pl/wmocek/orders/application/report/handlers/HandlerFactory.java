package pl.wmocek.orders.application.report.handlers;

/**
 * HandlerFactory creates Handler.
 */
@FunctionalInterface
public interface HandlerFactory {
    Handler create();
}
