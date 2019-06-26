package pl.nstrefa.wojciechmocek.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class OrderTest {

    @Test
    void whenDataIsCorrectThenOrderShouldBeCreated() {
        new Order("1", 1, "pizza", 1, 16.23);
    }

    @Test
    void orderNameCannotBeLongerThan255Chars() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new Order("1", 1, "a".repeat(256), 1, 16.23)
        );
    }

    @Test
    void orderNameMustBeAlphaNumeric() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new Order("1", 1, "some name $#@!", 1, 16.23),
            "Name must contain only alphanumeric chars"
        );
    }

    @ParameterizedTest
    @MethodSource("orderWithMissingParamsProvider")
    void itIsImpossibleToCreateOrderWhenClientIdOrNameIsMissing(
            String clientId,
            long requestId,
            String name,
            int quantity,
            double price
        ) {

        Assertions.assertThrows(
            NullPointerException.class,
            () -> new Order(clientId, requestId, name, quantity, price)
        );
    }

    static Stream<Arguments> orderWithMissingParamsProvider() {
        return Stream.of(
            Arguments.of(null, 1, "some name", 2, 16.23),
            Arguments.of("aa", 1, null, 2, 16.23));
    }
}
