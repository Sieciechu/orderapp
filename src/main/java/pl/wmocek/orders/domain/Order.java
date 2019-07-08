package pl.wmocek.orders.domain;

import lombok.NonNull;

import java.util.*;

public class Order {
    private ClientId clientId;
    private RequestId requestId;
    private final List<Product> products;

    public Order(
        @NonNull ClientId clientId,
        @NonNull RequestId requestId,
        @NonNull List<Product> productList
    ) {
        this.clientId=(clientId);
        this.requestId =(requestId);

        if (0 == productList.size()) {
            throw new IllegalArgumentException("Cannot create order without products");
        }
        this.products = new ArrayList<>(productList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return clientId.equals(order.clientId) &&
            requestId.equals(order.requestId) &&
            products.equals(order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, requestId, products);
    }

    public ClientId getClientId() {
        return clientId;
    }

    public RequestId getRequestId() {
        return requestId;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Product p : products) {
            total += p.getTotalPrice();
        }
        return total;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }

    public static Order create(@NonNull String clientId, long requestId, @NonNull List<Product> productList) {
        return new Order(
            new ClientId(clientId),
            new RequestId(requestId),
            productList
        );
    }

    public static Order create(@NonNull String clientId, long requestId, @NonNull Product product) {
        return new Order(
            new ClientId(clientId),
            new RequestId(requestId),
            Arrays.asList(product)
        );
    }
}
