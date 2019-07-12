package pl.wmocek.orders.application.report;

import pl.wmocek.orders.application.report.InMemoryRequestBus;
import pl.wmocek.orders.application.report.RequestBus;
import pl.wmocek.orders.domain.OrdersRepository;

public class CommandBusFactory {
    private final OrdersRepository ordersRepository;

    public CommandBusFactory(OrdersRepository o) {
        ordersRepository = o;
    }

    public RequestBus create() {
        return new InMemoryRequestBus(ordersRepository);
    }
}
