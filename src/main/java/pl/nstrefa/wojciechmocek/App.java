
package pl.nstrefa.wojciechmocek;

import lombok.NonNull;
import pl.nstrefa.wojciechmocek.domain.Order;
import pl.nstrefa.wojciechmocek.domain.OrderAlreadyExistsException;
import pl.nstrefa.wojciechmocek.domain.OrdersRepository;
import pl.nstrefa.wojciechmocek.infrastructure.FileReaderResolver;
import pl.nstrefa.wojciechmocek.infrastructure.InMemoryOrdersRepository;
import pl.nstrefa.wojciechmocek.infrastructure.Reader;
import pl.nstrefa.wojciechmocek.infrastructure.ReaderException;

public class App {

    private final String FILES_DIR_PATH = "/home/wojciech/Projects/orderapp2/src/main/resources/";
    private OrdersRepository ordersRepository;
    private FileReaderResolver readerFactory;

    private App(@NonNull OrdersRepository o, @NonNull FileReaderResolver r) {
        this.ordersRepository = o;
        this.readerFactory = r;
    }

    private void feedOrdersRepository(@NonNull String[] fileNames) {
        for (String fileName : fileNames) {
            Reader reader = null;
            try {
                reader = readerFactory.createReader(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Order o = null;
            while (reader.hasNext()) {
                try {

                    o = reader.read();
                    if (null == o) {
                        break;
                    }

                    ordersRepository.add(o);

                } catch (ReaderException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        var app = new App(
            new InMemoryOrdersRepository(),
            new FileReaderResolver()
        );
        app.feedOrdersRepository(args);
        app.run();
    }

    private void run() {
    }
}
