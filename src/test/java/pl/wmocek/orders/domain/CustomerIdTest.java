package pl.wmocek.orders.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CustomerIdTest {

    @Test
    void whenDataIsCorrectThenClientShouldBeCreated() {
        new CustomerId("1");
    }

    @Test
    void customerIdCannotBeLongerThan6Chars() {

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new CustomerId("1324567"),
            "CustomerId cannot be longer than 6 chars"
        );


    }

    @Test
    void customerIdMustBeAlphaNumeric() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new CustomerId("#$abc"),
            "CustomerId must contain only alphanumeric chars"
        );
    }

    @Test
    void testSameCustomerId() {
        Assertions.assertEquals(new CustomerId("aa"), new CustomerId("aa"));
    }
}
