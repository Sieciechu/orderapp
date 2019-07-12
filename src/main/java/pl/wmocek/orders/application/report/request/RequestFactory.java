package pl.wmocek.orders.application.report.request;

/**
 * RequestFactory creates the Report Request.
 */
@FunctionalInterface
public interface RequestFactory {

    /**
     * @param data Usually CommandData is used to pass into the Request constructor
     * @return Request
     */
    Request create(CommandData data);
}
