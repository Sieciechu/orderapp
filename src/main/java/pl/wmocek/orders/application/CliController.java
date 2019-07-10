package pl.wmocek.orders.application;

import pl.wmocek.orders.application.command.*;
import pl.wmocek.orders.domain.DistinctCustomersRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CliController implements Controller {

    private DistinctCustomersRepository repository;
    private Scanner scanner;
    private Map< String, CommandFactory> router = new HashMap<>();


    CliController(DistinctCustomersRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;

        setRoute("as", CountAllOrdersScreenReport::new);
        setRoute("bs", CountAllOrdersForCustomerScreenReport::new);
        setRoute("cs", TotalPriceOfAllOrdersScreenReport::new);
        setRoute("ds", TotalPriceOfAllOrdersForCustomerScreenReport::new);
//        router.put("ac", () -> new CountAllOrdersCSVReport());
//        router.put("ec", () -> new CreateAllOrdersCSVReport());
        setRoute("es", CreateAllOrdersScreenReport::new);
//        router.put("fc", () -> new CreateOrdersForCustomerCSVReport());
        setRoute("fs", ListAllOrdersForCustomerScreenReport::new);
        setRoute("gs", AveragePriceOfAllOrdersScreenReport::new);
        setRoute("hs", AveragePriceOfAllOrdersForCustomerScreenReport::new);

    }

    private void setRoute(String key, CommandFactory factory) {
        router.put(key, factory);
    }

    public Command getCommand(){

        printMenu();

        String chosenOption = scanner.nextLine();
        if ("q".equals(chosenOption)) {
            return null;
        }

        CommandData commandData = new CommandData();

        switch (chosenOption) {
            case "b": case "d": case "f": case "h":
                String customer = chooseCustomer();
                commandData.put("customerId", customer);
                break;
            default:
                break;
        }

        String reportDestination = chooseDestination();
        String chosenRoute = chosenOption + reportDestination;

        CommandFactory commandFactory = router.get(chosenRoute);
        if (null == commandFactory) {
            return null;
        }


        Command c = commandFactory.create(commandData);
        return c;

        /*
        if ("a".equals(chosenOption)) {
            a = "The total number of all orders";
            result = String.valueOf(ordersRepository.countAllOrders());
        }

        if ("b".equals(chosenOption)) {
            System.out.println("List of customers:");
            ordersRepository.getDistinctCustomers().forEach(customerId -> {
                System.out.println(" - '" + customerId + "'");
            });
            System.out.print("Please choose the customer by typing the client id: ");
            String client = scanner.nextLine();

            a = "Total number of all orders for customer '" + client + "'";
            result = String.valueOf(ordersRepository.countOrdersForCustomer(new CustomerId(client)));
        }

        if ("c".equals(chosenOption)) {
            a = "Total price of all orders";
            result = String.valueOf(ordersRepository.sumPriceOfAllOrders());
        }

        if ("d".equals(chosenOption)) {
            System.out.println("List of customers:");
            ordersRepository.getDistinctCustomers().forEach(customerId -> {
                System.out.println(" - '" + customerId + "'");
            });
            System.out.print("Please choose the customer by typing the client id: ");
            String client = scanner.nextLine();

            a = "Total price of all orders for customer '" + client + "'";
            result = String.valueOf(ordersRepository.sumPriceOfOrdersForCustomer(new CustomerId(client)));
        }

        if ("e".equals(chosenOption)) {
            a = "List of all orders";
            var orders = ordersRepository.getAll();
            result = "";
        }

        if ("f".equals(chosenOption)) {
            System.out.println("List of customers:");
            ordersRepository.getDistinctCustomers().forEach(customerId -> {
                System.out.println(" - '" + customerId + "'");
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
            ordersRepository.getDistinctCustomers().forEach(customerId -> {
                System.out.println(" - '" + customerId + "'");
            });
            System.out.print("Please choose the customer by typing the client id: ");
            String client = scanner.nextLine();

            a = "Average price of all orders for customer '" + client + "'";
            result = String.valueOf(ordersRepository.getAveragePriceOfOrderForCustomer(new CustomerId(client)));
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
        */

    }

    private String chooseDestination() {
        printReportDestination();
        String destination = scanner.nextLine();
        if ("".equals(destination)) {
            destination = "s";
        }
        return destination;
    }

    private String chooseCustomer() {
        printDistinctCustomers();
        System.out.print("Please choose the customer by typing the client id: ");
        return scanner.nextLine();
    }

    private void printDistinctCustomers() {
        System.out.println("List of customers:");
        repository.getDistinctCustomers().forEach(customerId -> {
            System.out.println(" - '" + customerId + "'");
        });
    }

    private void printReportDestination() {
        System.out.print(
            "Report's destination:\n" +
                "s - screen\n" +
                "c - csv file\n" +
                "----------------------\n" +
                "Please choose the destination (default s): "
        );
    }

    private void printMenu() {
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
    }
}
