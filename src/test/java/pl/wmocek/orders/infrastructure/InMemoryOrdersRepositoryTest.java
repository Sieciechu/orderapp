package pl.wmocek.orders.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.wmocek.orders.domain.CustomerId;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.domain.OrderAlreadyExistsException;
import pl.wmocek.orders.domain.Product;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class InMemoryOrdersRepositoryTest {

    private InMemoryOrdersRepository repository;

    @Test
    void store() throws OrderAlreadyExistsException {

        // given
        repository = new InMemoryOrdersRepository();

        List<Product> firstShopping = Arrays.asList(
            new Product("bread", 1, 2.5),
            new Product("chocolate", 2, 1.5)
        );

        // when
        repository.store(Order.create("1", 1, firstShopping));


        // then
        List<Order> orders = repository.getAll();

        Assertions.assertEquals(1, orders.size());

        Order expectedOrder = Order.create("1", 1, Arrays.asList(
            new Product("bread", 1, 2.5),
            new Product("chocolate", 2, 1.5)
        ));

        Assertions.assertEquals(expectedOrder, orders.get(0));
    }

    @Test
    void whenRequestExistsThenItIsImpossibleToStoreOrderWithSameRequest() throws OrderAlreadyExistsException {

        // given
        repository = new InMemoryOrdersRepository();

        List<Product> firstShopping = Arrays.asList(
            new Product("bread", 1, 2.5),
            new Product("chocolate", 2, 1.5)
        );
        repository.store(Order.create("1", 1, firstShopping));

        // when-then
        List<Product> secondRequest = Arrays.asList(
            new Product("bread", 1, 2.5),
            new Product("chocolate", 2, 1.5)
        );

        Assertions.assertThrows(
            OrderAlreadyExistsException.class,
            () -> repository.store(Order.create("1", 1, secondRequest))
        );
    }

    @Test
    void whenOrderDoesNotExistItIsAllowedToAddOrder() {

        // given
        repository = new InMemoryOrdersRepository();

        List<Product> firstShopping = Arrays.asList(
            new Product("bread", 1, 2.5),
            new Product("chocolate", 2, 1.5)
        );

        // when
        repository.add(Order.create("1", 1, firstShopping));


        // then
        List<Order> orders = repository.getAll();


        Order expectedOrder = Order.create("1", 1, Arrays.asList(
            new Product("bread", 1, 2.5),
            new Product("chocolate", 2, 1.5)
        ));
        Assertions.assertEquals(1, orders.size());
        Assertions.assertEquals(expectedOrder, orders.get(0));
    }

    @Test
    void whenOrderAlreadyExistItIsAllowedToAddOrderToExistingOne() {

        // given
        repository = new InMemoryOrdersRepository();

        List<Product> firstShopping = Arrays.asList(
            new Product("bread", 1, 2.5),
            new Product("chocolate", 2, 1.5)
        );
        repository.add(Order.create("1", 1, firstShopping));

        // when
        List<Product> newThingsToSameOrder = Arrays.asList(new Product("candy", 3, 2.5));
        repository.add(Order.create("1", 1, newThingsToSameOrder));


        // then
        List<Order> orders = repository.getAll();
        Assertions.assertEquals(1, orders.size());

        Order expectedOrder = Order.create("1", 1, Arrays.asList(
            new Product("bread", 1, 2.5),
            new Product("chocolate", 2, 1.5),
            new Product("candy", 3, 2.5)
        ));

        Assertions.assertEquals(expectedOrder, orders.get(0));
    }

    @ParameterizedTest
    @MethodSource("arrayOfOrdersProvider")
    void countAllOrders(int expectedOrdersCount, Order[] orders) {
        repository = new InMemoryOrdersRepository();
        for (Order o : orders) {
            repository.add(o);
        }

        Assertions.assertEquals(expectedOrdersCount, repository.countAllOrders());
    }

    static Stream<Arguments> arrayOfOrdersProvider() {
        return Stream.of(
            Arguments.of(
                1,
                new Order[]{
                    Order.create("1", 1, new Product("bread", 1, 2.5))
                }
            ),
            Arguments.of(
                1,
                new Order[]{
                    Order.create("1", 1, new Product("bread", 1, 2.5)),
                    Order.create("1", 1, new Product("butter", 1, 1.5)),
                }
            ),
            Arguments.of(
                2,
                new Order[]{
                    Order.create("1", 1, new Product("bread", 1, 2.5)),
                    Order.create("1", 1, new Product("butter", 1, 1.5)),
                    Order.create("2", 2, new Product("ham", 1, 7)),
                }
            ),
            Arguments.of(
                2,
                new Order[]{
                    Order.create("1", 1, new Product("bread", 1, 2.5)),
                    Order.create("1", 1, new Product("butter", 1, 1.5)),
                    Order.create("2", 2, new Product("bread", 1, 2.5)),
                    Order.create("2", 2, new Product("ham", 1, 7)),
                }
            ),
            Arguments.of(
                3,
                new Order[]{
                    Order.create("1", 1, new Product("bread", 1, 2.5)),
                    Order.create("2", 2, new Product("butter", 1, 1.5)),
                    Order.create("3", 3, new Product("ham", 1, 7)),
                }
            ),
            Arguments.of(
                4,
                new Order[]{
                    Order.create("1", 1, new Product("bread", 1, 2.5)),
                    Order.create("1", 2, new Product("butter", 1, 1.5)),
                    Order.create("1", 3, new Product("ham", 1, 7)),
                    Order.create("1", 4, new Product("candy", 1, 1.0)),
                }
            )
        );
    }

    @Test
    void countOrdersForCustomer() {
        repository = new InMemoryOrdersRepository();
        repository.add(Order.create("1", 1, new Product("bread", 1, 2.5)));
        repository.add(Order.create("1", 1, new Product("chocolate", 2, 1.5)));
        repository.add(Order.create("1", 3, new Product("butter", 1, 3.0)));
        repository.add(Order.create("2", 5, new Product("bread", 2, 2.5)));
        repository.add(Order.create("3", 6, new Product("butter", 1, 3.0)));

        Assertions.assertEquals(2, repository.countOrdersForCustomer(new CustomerId("1")));
        Assertions.assertEquals(1, repository.countOrdersForCustomer(new CustomerId("2")));
        Assertions.assertEquals(1, repository.countOrdersForCustomer(new CustomerId("3")));
    }

    @Test
    void sumPriceOfAllOrders() throws OrderAlreadyExistsException {
        repository = new InMemoryOrdersRepository();
        repository.store(Order.create("1", 1, new Product("bread", 1, 2.5)));
        repository.store(Order.create("1", 2, new Product("chocolate", 2, 1.5)));
        repository.store(Order.create("2", 3, new Product("butter", 1, 3.0)));
        repository.store(Order.create("2", 4, new Product("ham", 3, 10.0)));
        Assertions.assertEquals(38.5, repository.sumPriceOfAllOrders());
    }

    @Test
    void sumPriceOfOrdersForCustomer() throws OrderAlreadyExistsException {
        repository = new InMemoryOrdersRepository();
        repository.store(Order.create("1", 1, new Product("bread", 1, 2.5)));
        repository.store(Order.create("1", 2, new Product("butter", 1, 3.0)));
        repository.store(Order.create("1", 3, new Product("chocolate", 2, 1.5)));
        repository.store(Order.create("2", 4, new Product("ham", 3, 10.0)));
        repository.store(Order.create("3", 5, new Product("ham", 1, 10.0)));

        Assertions.assertEquals(8.5, repository.sumPriceOfOrdersForCustomer(new CustomerId("1")));
    }

    @Test
    void getAll() throws OrderAlreadyExistsException {
        repository = new InMemoryOrdersRepository();
        repository.store(Order.create("1", 1, new Product("bread", 1, 2.5)));
        repository.store(Order.create("1", 5, new Product("butter", 1, 3.0)));
        repository.store(Order.create("1", 2, new Product("chocolate", 2, 1.5)));
        repository.store(Order.create("2", 3, new Product("ham", 3, 10.0)));
        repository.store(Order.create("3", 4, new Product("ham", 1, 10.0)));

        List<Order> orders = repository.getAll();
        Assertions.assertEquals(5, orders.size());
    }

    @Test
    void getOrdersForCustomer() {
        repository = new InMemoryOrdersRepository();
        repository.add(Order.create("1", 1, new Product("bread", 1, 2.5)));
        repository.add(Order.create("1", 1, new Product("butter", 1, 3.0)));
        repository.add(Order.create("1", 2, new Product("chocolate", 2, 1.5)));

        repository.add(Order.create("2", 3, new Product("ham", 3, 10.0)));

        repository.add(Order.create("3", 4, new Product("ham", 1, 10.0)));

        List<Order> orders = repository.getOrdersForCustomer(new CustomerId("1"));
        Assertions.assertEquals(2, orders.size());
        Assertions.assertEquals(
            Order.create("1", 1, Arrays.asList(
                new Product("bread", 1, 2.5),
                new Product("butter", 1, 3.0)
            )),
            orders.get(0)
        );
        Assertions.assertEquals(
            Order.create("1", 2, new Product("chocolate", 2, 1.5)),
            orders.get(1)
        );

    }

    @Test
    void getAveragePriceOfOrder() throws OrderAlreadyExistsException {
        repository = new InMemoryOrdersRepository();
        repository.add(Order.create("1", 1, new Product("bread", 1, 2.5)));
        repository.add(Order.create("1", 1, new Product("butter", 1, 3.0)));
        repository.add(Order.create("2", 3, new Product("chocolate", 2, 1.5)));
        repository.add(Order.create("3", 4, new Product("ham", 3, 10.0)));


        Assertions.assertEquals(
            (2.5 + 3.0 + 2*1.5 + 3*10.0) / 3,
            repository.getAveragePriceOfOrder()
        );
    }

    @Test
    void getAveragePriceOfOrderForCustomer() {
        // given
        repository = new InMemoryOrdersRepository();

        repository.add(Order.create("1", 1, new Product("bread", 1, 4.0)));
        repository.add(Order.create("1", 2, new Product("butter", 1, 6.0)));

        repository.add(Order.create("2", 3, new Product("ham", 2, 5.0)));
        repository.add(Order.create("2", 3, new Product("candy", 1, 4.0)));

        repository.add(Order.create("3", 4, Arrays.asList(
            new Product("ham", 1, 10.0),
            new Product("mild", 2, 4.0)
        )));
        repository.add(Order.create("3", 5, new Product("butter", 1, 6.0)));

        // when-then
        Assertions.assertEquals(5.0, repository.getAveragePriceOfOrderForCustomer(new CustomerId("1")));
        Assertions.assertEquals(14.0, repository.getAveragePriceOfOrderForCustomer(new CustomerId("2")));
        Assertions.assertEquals(12.0, repository.getAveragePriceOfOrderForCustomer(new CustomerId("3")));
    }

    @Test
    void getDistinctClients() {
        // given
        repository = new InMemoryOrdersRepository();

        repository.add(Order.create("1", 1, new Product("bread", 1, 4.0)));
        repository.add(Order.create("1", 2, new Product("butter", 1, 6.0)));
        repository.add(Order.create("2", 3, new Product("ham", 2, 5.0)));
        repository.add(Order.create("2", 3, new Product("candy", 1, 4.0)));
        repository.add(Order.create("3", 5, new Product("butter", 1, 6.0)));

        // when-then
        List<CustomerId> clients = repository.getDistinctCustomers();
        Assertions.assertEquals(3, clients.size());
        Assertions.assertTrue(clients.contains(new CustomerId("1")));
        Assertions.assertTrue(clients.contains(new CustomerId("2")));
        Assertions.assertTrue(clients.contains(new CustomerId("3")));

    }

    @Test
    void whenReadRepositoryThenOrdersBufferIsFilledWithOrders() throws IOException {
        // given
        repository = new InMemoryOrdersRepository();
        repository.add(Order.create("1", 1, new Product("bread", 1, 2.5)));
        repository.add(Order.create("1", 1, new Product("chocolate", 2, 1.5)));
        repository.add(Order.create("1", 3, new Product("butter", 1, 3.0)));
        repository.add(Order.create("2", 5, new Product("bread", 2, 2.5)));
        repository.add(Order.create("3", 6, new Product("butter", 1, 3.0)));

        Order[] ordersBuffer = new Order[10];

        // when
        int numberOfReadOrders = repository.read(ordersBuffer);

        // then
        Assertions.assertEquals(4, numberOfReadOrders);

        Order expectedOrder = Order.create("1", 1, Arrays.asList(
            new Product("bread", 1, 2.5),
            new Product("chocolate", 2, 1.5)
        ));
        Assertions.assertEquals(expectedOrder, ordersBuffer[0]);


        for (int i = numberOfReadOrders; i < ordersBuffer.length; i++) {
            Assertions.assertNull(ordersBuffer[i]);
        }
    }

    @Test
    void whenThereAreNoMoreOrdersToReadThenOrdersBufferIsEmpty() throws IOException {
        // given
        repository = new InMemoryOrdersRepository();
        repository.add(Order.create("1", 1, new Product("bread", 1, 2.5)));
        repository.add(Order.create("1", 1, new Product("chocolate", 2, 1.5)));
        repository.add(Order.create("1", 3, new Product("butter", 1, 3.0)));
        repository.add(Order.create("2", 5, new Product("bread", 2, 2.5)));
        repository.add(Order.create("3", 6, new Product("butter", 1, 3.0)));

        Order[] ordersBuffer = new Order[9];
        int numberOfReadOrders = 0;
        numberOfReadOrders = repository.read(ordersBuffer);

        // when-then
        numberOfReadOrders = repository.read(ordersBuffer);
        Assertions.assertNull(numberOfReadOrders);
        Assertions.assertArrayEquals(new Order[9], ordersBuffer);

        numberOfReadOrders = repository.read(ordersBuffer);
        Assertions.assertNull(numberOfReadOrders);
        Assertions.assertArrayEquals(new Order[9], ordersBuffer);
    }
}
