package pl.wmocek.orders.application;

import pl.wmocek.orders.domain.OrdersRepository;
import pl.wmocek.orders.infrastructure.InMemoryOrdersRepository;
import pl.wmocek.orders.io.OrderReader;

public class CommandBusFactory {
    private final OrderReader orderReader;
    private final OrdersRepository ordersRepository;

    public CommandBusFactory(OrderReader r, OrdersRepository o) {
        orderReader = r;
        ordersRepository = o;
    }

    public CommandBus create() {
        return new InMemoryCommandBus(orderReader, ordersRepository);
    }
}
