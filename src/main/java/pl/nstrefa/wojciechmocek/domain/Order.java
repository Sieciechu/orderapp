package pl.nstrefa.wojciechmocek.domain;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private ClientId clientId;
    private long requestId;
    private final List<Product> products;

    public Order(@NonNull String clientId, long requestId) {
        this(clientId, requestId, new ArrayList<>());
    }

    public Order(@NonNull String clientId, long requestId, @NonNull List<Product> productList) {
        this.clientId = new ClientId(clientId);
        this.requestId = requestId;
        this.products = new ArrayList<>(productList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return requestId == order.requestId &&
            clientId.equals(order.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, requestId);
    }

    public ClientId getClientId() {
        return clientId;
    }

    public long getRequestId() {
        return requestId;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Product p : products) {
            total += p.getTotalPrice();
        }
        return total;
    }
}
