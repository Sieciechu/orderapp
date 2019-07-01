package pl.nstrefa.wojciechmocek.domain;

import lombok.NonNull;

import java.util.Objects;

public class Order {
    private ClientId clientId;
    private long requestId;
    private String name;
    private int quantity;
    private double unitPrice;

    public Order(@NonNull String clientId, long requestId, @NonNull String name, int quantity, double unitPrice) {
        this.clientId = new ClientId(clientId);
        this.requestId = requestId;
        setName(name);
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    private void setName(@NonNull String name) {
        if (255 < name.length()) {
            throw new IllegalArgumentException("Name cannot be longer than 255 chars");
        }

        if (!name.matches("[A-Za-z0-9ąćęłńóśźżĄĘŁŃÓŚŹŻ ]+")) {
            throw new IllegalArgumentException("Name must contain only alphanumeric chars");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return requestId == order.requestId &&
            quantity == order.quantity &&
            Double.compare(order.unitPrice, unitPrice) == 0 &&
            clientId.equals(order.clientId) &&
            name.equals(order.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, requestId, name, quantity, unitPrice);
    }
}
