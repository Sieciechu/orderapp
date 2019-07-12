package pl.wmocek.orders.application.report.handlers;

import lombok.NonNull;
import pl.wmocek.orders.domain.OrdersRepository;

abstract class OrderedBasedReport implements Handler {
    protected final OrdersRepository ordersRepository;

    public OrderedBasedReport(@NonNull OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }
}
