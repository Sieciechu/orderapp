package pl.nstrefa.wojciechmocek.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void getTotalPrice() {
        Product p = new Product("some product name", 3, 3.1);
        Assertions.assertEquals(9.3, p.getTotalPrice());
    }

    @Test
    void orderNameCannotBeLongerThan255Chars() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new Product("a".repeat(256), 1, 16.23)
        );
    }

    @Test
    void orderNameMustBeAlphaNumeric() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new Product("some name $#@!", 1, 16.23),
            "Name must contain only alphanumeric chars"
        );
    }
}