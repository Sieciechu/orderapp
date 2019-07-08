package pl.wmocek.orders.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


class OrderTest {

    @Test
    void whenDataIsCorrectThenOrderShouldBeCreated() {
        new Order(
            new ClientId("1"),
            new RequestId(1),
            Arrays.asList(new Product("pizza", 1, 16.23))
        );
    }



    @ParameterizedTest
    @MethodSource("orderWithMissingParamsProvider")
    void itIsImpossibleToCreateOrderWhenClientIdOrNameIsMissing(
            ClientId clientId,
            RequestId requestId,
            List<Product> products
        ) {


        Assertions.assertThrows(
            NullPointerException.class,
            () -> new Order(clientId, requestId, products)
        );
    }

    static Stream<Arguments> orderWithMissingParamsProvider() {
        List<Product> exampleProducts = Arrays.asList(new Product("some name", 2, 16.23));
        return Stream.of(
            Arguments.of(null, new RequestId(1), exampleProducts),
            Arguments.of(new ClientId("aa"), null, exampleProducts),
            Arguments.of(new ClientId("aa"), new RequestId(1), null)
        );
    }
}
