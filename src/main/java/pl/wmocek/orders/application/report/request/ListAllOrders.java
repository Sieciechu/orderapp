package pl.wmocek.orders.application.report.request;

public class ListAllOrders extends Base implements ListRequest {
    public ListAllOrders() {
        super();
    }

    public ListAllOrders(CommandData commandData) {
        super(commandData);
    }
}