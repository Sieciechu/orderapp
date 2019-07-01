package pl.nstrefa.wojciechmocek.domain;

import lombok.NonNull;

import java.util.Objects;

public final class ClientId {
    private final String value;

    public ClientId(@NonNull String value) {
        if (6 < value.length()) {
            throw new IllegalArgumentException("ClientId cannot be longer than 6 chars");
        }
        if (!value.matches("[A-Za-z0-9ąćęłńóśźżĄĘŁŃÓŚŹŻ ]+")) {
            throw new IllegalArgumentException("ClientId must contain only alphanumeric chars");
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
        ClientId clientId = (ClientId) o;
        return value.equals(clientId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
