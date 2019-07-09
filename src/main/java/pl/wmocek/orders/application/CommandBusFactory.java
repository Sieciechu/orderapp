package pl.wmocek.orders.application;

import pl.wmocek.orders.infrastructure.InMemoryOrdersRepository;
import pl.wmocek.orders.io.OrderReader;

public class CommandBusFactory {
    private final OrderReader orderReader;

    public CommandBusFactory(OrderReader r) {
        orderReader = r;
    }

    public CommandBus create() {
        return new InMemoryCommandBus(orderReader);
    }
}
