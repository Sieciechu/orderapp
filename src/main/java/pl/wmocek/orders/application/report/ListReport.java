package pl.wmocek.orders.application.report;

import pl.wmocek.orders.domain.Order;

public class ListReport implements Report {
    private final String title;
    private final Order[] orders;

    public ListReport(String title, Order[] orders) {
        this.title = title;
        this.orders = orders;
    }

    public String getTitle() {
        return title;
    }

    public Order[] getOrders() {
        return orders;
    }
}
