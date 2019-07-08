package pl.wmocek.orders.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ClientIdTest {

    @Test
    void whenDataIsCorrectThenClientShouldBeCreated() {
        new ClientId("1");
    }

    @Test
    void clientIdCannotBeLongerThan6Chars() {

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new ClientId("1324567"),
            "ClientId cannot be longer than 6 chars"
        );


    }

    @Test
    void clientIdMustBeAlphaNumeric() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new ClientId("#$abc"),
            "ClientId must contain only alphanumeric chars"
        );
    }

    @Test
    void testSameClientId() {
        Assertions.assertEquals(new ClientId("aa"), new ClientId("aa"));
    }
}
