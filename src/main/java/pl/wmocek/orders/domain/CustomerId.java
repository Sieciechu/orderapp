package pl.wmocek.orders.domain;

import lombok.NonNull;

import java.util.Objects;

public final class CustomerId {
    private final String value;

    public CustomerId(@NonNull String value) {
        if (6 < value.length()) {
            throw new IllegalArgumentException("CustomerId cannot be longer than 6 chars");
        }
        if (!value.matches("[A-Za-z0-9ąćęłńóśźżĄĘŁŃÓŚŹŻ]+")) {
            throw new IllegalArgumentException("CustomerId must contain only alphanumeric chars without spaces");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerId customerId = (CustomerId) o;
        return value.equals(customerId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
