
package pl.nstrefa.wojciechmocek;

import lombok.NonNull;
import pl.nstrefa.wojciechmocek.domain.ClientId;
import pl.nstrefa.wojciechmocek.domain.Order;
import pl.nstrefa.wojciechmocek.domain.OrdersRepository;
import pl.nstrefa.wojciechmocek.infrastructure.FileReaderResolver;
import pl.nstrefa.wojciechmocek.infrastructure.InMemoryOrdersRepository;
import pl.nstrefa.wojciechmocek.infrastructure.Reader;
import pl.nstrefa.wojciechmocek.infrastructure.ReaderException;

import java.io.*;
import java.util.Scanner;

public class App {

    private final String FILES_DIR_PATH = "/home/wojciech/Projects/orderapp2/src/main/resources/";
    private OrdersRepository ordersRepository;
    private FileReaderResolver readerFactory;
    private Scanner scanner;

    private App(@NonNull OrdersRepository o, @NonNull FileReaderResolver r, Scanner s) {
        ordersRepository = o;
        readerFactory = r;
        scanner = s;
    }

    public static void main(String[] args) throws IOException {

        var app = new App(
            new InMemoryOrdersRepository(),
            new FileReaderResolver(),
            new Scanner(System.in)
        );
        app.feedOrdersRepository(args);
        app.run();
    }

    private void run() throws IOException {
        System.out.print(
            "Welcome to the Shop.\n" +
            "----------------------------------------------------\n" +
            "List of available reports:\n" +
            "q - Quit\n" +
            "a - Count all orders,\n" +
            "b - Count orders for given customer,\n" +
            "c - Total price of all orders,\n" +
            "d - Total price of all orders for given customer\n" +
            "e - List of all orders,\n" +
            "f - List of orders for given customer,\n" +
            "g - Average price of all orders,\n" +
            "h - Average price of all orders for given customer.\n" +
            "----------------------------------------------------\n" +
            "Please choose the action: "
        );

        String chosenOption = scanner.nextLine();
        if ("q".equals(chosenOption)) {
            return;
        }
        String a = "";
        String result = "";

        if ("a".equals(chosenOption)) {
            a = "The total number of all orders";
            result = String.valueOf(ordersRepository.countAllOrders());
        }

        if ("b".equals(chosenOption)) {
            System.out.println("List of customers:");
            ordersRepository.getDistinctClients().forEach(clientId -> {
                System.out.println(" - '" + clientId + "'");
            });
            System.out.print("Please choose the customer by typing the client id: ");
            String client = scanner.nextLine();

            a = "Total number of all orders for customer '" + client + "'";
            result = String.valueOf(ordersRepository.countOrdersForCustomer(new ClientId(client)));
        }

        if ("c".equals(chosenOption)) {
            a = "Total price of all orders";
            result = String.valueOf(ordersRepository.sumPriceOfAllOrders());
        }

        if ("d".equals(chosenOption)) {
            System.out.println("List of customers:");
            ordersRepository.getDistinctClients().forEach(clientId -> {
                System.out.println(" - '" + clientId + "'");
            });
            System.out.print("Please choose the customer by typing the client id: ");
            String client = scanner.nextLine();

            a = "Total price of all orders for customer '" + client + "'";
            result = String.valueOf(ordersRepository.sumPriceOfOrdersForCustomer(new ClientId(client)));
        }

        if ("e".equals(chosenOption)) {
            a = "List of all orders";
            var orders = ordersRepository.getAll();
            result = "";
        }

        if ("f".equals(chosenOption)) {
            System.out.println("List of customers:");
            ordersRepository.getDistinctClients().forEach(clientId -> {
                System.out.println(" - '" + clientId + "'");
            });
            System.out.print("Please choose the customer by typing the client id: ");
            String client = scanner.nextLine();

            a = "List of orders for customer '" + client + "'";
            result = "";
        }

        if ("g".equals(chosenOption)) {
            a = "Average price of all orders";
            result = String.valueOf(ordersRepository.getAveragePriceOfOrder());
        }

        if ("h".equals(chosenOption)) {
            System.out.println("List of customers:");
            ordersRepository.getDistinctClients().forEach(clientId -> {
                System.out.println(" - '" + clientId + "'");
            });
            System.out.print("Please choose the customer by typing the client id: ");
            String client = scanner.nextLine();

            a = "Average price of all orders for customer '" + client + "'";
            result = String.valueOf(ordersRepository.getAveragePriceOfOrderForCustomer(new ClientId(client)));
        }

        System.out.print(
            "Report's destination:\n" +
                "s - screen\n" +
                "c - csv file\n" +
                "----------------------\n" +
                "Please choose the destination: "
        );
        String reportDestination = scanner.nextLine();
        Writer writer = null;
        if ("s".equals(reportDestination)) {
            writer = new BufferedWriter(new OutputStreamWriter(System.out));
        } else if ("c".equals(reportDestination)) {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(FILES_DIR_PATH + "report.csv")));
        }
        writer.write(a + "\n");
        writer.write(result + "\n");
        writer.flush();

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

}
