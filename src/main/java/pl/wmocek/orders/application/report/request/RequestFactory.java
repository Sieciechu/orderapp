package pl.wmocek.orders.application.report.request;

@FunctionalInterface
public interface RequestFactory {
    Request create(CommandData data);
}
