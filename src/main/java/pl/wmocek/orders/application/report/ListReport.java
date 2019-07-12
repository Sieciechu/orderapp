package pl.wmocek.orders.application.report;

import pl.wmocek.orders.domain.Order;

import java.util.List;

public class ListReport implements Report {
    private final String title;
    private final List<Order> orders;

    public ListReport(String title, List<Order> orderList) {
        this.title = title;
        this.orders = orderList;
    }

    public String getTitle() {
        return title;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
