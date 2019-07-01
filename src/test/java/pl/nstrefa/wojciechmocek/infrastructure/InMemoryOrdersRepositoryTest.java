package pl.nstrefa.wojciechmocek.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.nstrefa.wojciechmocek.domain.ClientId;
import pl.nstrefa.wojciechmocek.domain.Order;
import pl.nstrefa.wojciechmocek.domain.OrderAlreadyExistsException;

import java.util.Set;

class InMemoryOrdersRepositoryTest {

    private InMemoryOrdersRepository repository;

    @Test
    void store() throws OrderAlreadyExistsException {

        // given
        repository = new InMemoryOrdersRepository();
        repository.store(new Order("1", 1, "bread", 1, 2.5));
        repository.store(new Order("1", 3, "chocolate", 2, 1.5));
        repository.store(new Order("1", 2, "butter", 1, 3.0));

        int initCount = 3;


        // when
        repository.store(new Order("3", 5, "ham", 2, 7.0));

        // then
        Set<Order> orders = repository.getAll();
        Assertions.assertEquals(initCount+1, orders.size());
        Order expectedOrder = new Order("3", 5, "ham", 2, 7.0);
        Assertions.assertTrue(orders.contains(expectedOrder));
    }

    @Test
    void countAllOrders() throws OrderAlreadyExistsException {
        repository = new InMemoryOrdersRepository();
        repository.store(new Order("1", 1, "bread", 1, 2.5));
        repository.store(new Order("1", 2, "chocolate", 2, 1.5));
        repository.store(new Order("2", 3, "butter", 1, 3.0));
        repository.store(new Order("3", 4, "ham", 3, 7.0));

        Assertions.assertEquals(4, repository.countAllOrders());
    }

    @Test
    void countOrdersForCustomer() throws OrderAlreadyExistsException {
        repository = new InMemoryOrdersRepository();
        repository.store(new Order("1", 1, "bread", 1, 2.5));
        repository.store(new Order("1", 2, "chocolate", 2, 1.5));
        repository.store(new Order("1", 3, "butter", 1, 3.0));
        repository.store(new Order("2", 5, "bread", 2, 2.5));
        repository.store(new Order("3", 6, "butter", 1, 3.0));

        Assertions.assertEquals(3, repository.countOrdersForCustomer(new ClientId("1")));
    }

    @Test
    void sumPriceOfAllOrders() throws OrderAlreadyExistsException {
        repository = new InMemoryOrdersRepository();
        repository.store(new Order("1", 1, "bread", 1, 2.5));
        repository.store(new Order("1", 2, "chocolate", 2, 1.5));
        repository.store(new Order("2", 3, "butter", 1, 3.0));
        repository.store(new Order("2", 4, "ham", 3, 10.0));
        Assertions.assertEquals(38.5, repository.sumPriceofAllOrders());
    }

    @Test
    void sumPriceOfOrdersForCustomer() throws OrderAlreadyExistsException {
        repository = new InMemoryOrdersRepository();
        repository.store(new Order("1", 1, "bread", 1, 2.5));
        repository.store(new Order("1", 2, "butter", 1, 3.0));
        repository.store(new Order("1", 3, "chocolate", 2, 1.5));
        repository.store(new Order("2", 4, "ham", 3, 10.0));
        repository.store(new Order("3", 5, "ham", 1, 10.0));

        Assertions.assertEquals(8.5, repository.sumPriceOfOrdersForCustomer(new ClientId("1")));
    }

    @Test
    void getAll() throws OrderAlreadyExistsException {
        repository = new InMemoryOrdersRepository();
        repository.store(new Order("1", 1, "bread", 1, 2.5));
        repository.store(new Order("1", 5, "butter", 1, 3.0));
        repository.store(new Order("1", 2, "chocolate", 2, 1.5));
        repository.store(new Order("2", 3, "ham", 3, 10.0));
        repository.store(new Order("3", 4, "ham", 1, 10.0));

        Set<Order> orders = repository.getAll();
        Assertions.assertEquals(5, orders.size());
    }

    @Test
    void getOrdersForCustomer() throws OrderAlreadyExistsException {
        repository = new InMemoryOrdersRepository();
        repository.store(new Order("1", 1, "bread", 1, 2.5));
        repository.store(new Order("1", 5, "butter", 1, 3.0));
        repository.store(new Order("1", 2, "chocolate", 2, 1.5));
        repository.store(new Order("2", 3, "ham", 3, 10.0));
        repository.store(new Order("3", 4, "ham", 1, 10.0));

        var orders = repository.getOrdersForCustomer(new ClientId("1"));
        Assertions.assertEquals(3, orders.size());
    }

    @Test
    void getAveragePriceOfOrder() throws OrderAlreadyExistsException {
        repository = new InMemoryOrdersRepository();
        repository.store(new Order("1", 1, "bread", 1, 2.5));
        repository.store(new Order("1", 2, "butter", 1, 3.0));
        repository.store(new Order("2", 3, "chocolate", 2, 1.5));
        repository.store(new Order("3", 4, "ham", 3, 10.0));


        Assertions.assertEquals(
            (2.5 + 3.0 + 2*1.5 + 3*10.0) / 4,
            repository.getAveragePriceOfOrder()
        );
    }

    @Test
    void getAveragePriceOfOrderForCustomer() throws OrderAlreadyExistsException {
        repository = new InMemoryOrdersRepository();
        repository.store(new Order("1", 1, "bread", 1, 4.0));
        repository.store(new Order("1", 2, "butter", 1, 6.0));
        repository.store(new Order("2", 3, "ham", 3, 10.0));
        repository.store(new Order("3", 4, "ham", 1, 10.0));

        Assertions.assertEquals(5.0, repository.getAveragePriceOfOrderForCustomer(new ClientId("1")));

        repository = new InMemoryOrdersRepository();
        repository.store(new Order("1", 1, "bread", 2, 4.0));
        repository.store(new Order("1", 2, "butter", 3, 6.0));
        repository.store(new Order("2", 3, "ham", 3, 10.0));
        repository.store(new Order("3", 4, "ham", 1, 10.0));

        Assertions.assertEquals(13.0, repository.getAveragePriceOfOrderForCustomer(new ClientId("1")));


    }
}