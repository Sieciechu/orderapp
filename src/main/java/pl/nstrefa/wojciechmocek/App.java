
package pl.nstrefa.wojciechmocek;

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
            Order o = null;
            while(true) {
                    o = reader.read();
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
