package pl.wmocek.orders.application.report.request;

public class ListAllOrdersForCustomer extends Base implements ListRequest {
    public ListAllOrdersForCustomer() {
        super();
    }

    public ListAllOrdersForCustomer(CommandData commandData) {
        super(commandData);
    }
}