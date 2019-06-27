
package pl.nstrefa.wojciechmocek;

import pl.nstrefa.wojciechmocek.domain.Order;
import pl.nstrefa.wojciechmocek.domain.OrdersRepository;
import pl.nstrefa.wojciechmocek.infrastructure.InMemoryOrdersRepository;
import pl.nstrefa.wojciechmocek.infrastructure.FileReaderResolver;
import pl.nstrefa.wojciechmocek.infrastructure.Reader;

import java.io.IOException;

public class App {

    private final String FILES_DIR_PATH = "/home/wojciech/Projects/orderapp2/src/main/resources/";
    private OrdersRepository ordersRepository;
    private FileReaderResolver readerFactory;

    private App(OrdersRepository o, FileReaderResolver r) {
        this.ordersRepository = o;
        this.readerFactory = r;
    }

    private void feedOrdersRepository(String[] fileNames) {
        for(String fileName : fileNames){
            Reader reader = null;
            try {
                reader = readerFactory.createReader(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            if (null == reader) {
                continue;
            }

            Order o = null;
            while(true) {
                try {
                    o = reader.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (null == o) {
                    break;
                }

                ordersRepository.store(o);
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
