package pl.nstrefa.wojciechmocek.domain;

import lombok.NonNull;

import java.util.Objects;

public class Product {
    private String name;
    private int quantity;
    private double unitPrice;

    public Product(@NonNull String name, int quantity, double unitPrice) {
        setName(name);
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity &&
            Double.compare(product.unitPrice, unitPrice) == 0 &&
            name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, unitPrice);
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

    double getTotalPrice() {
        return quantity * unitPrice;
    }
}
