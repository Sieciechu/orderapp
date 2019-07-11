package pl.wmocek.orders.application;

import pl.wmocek.orders.application.report.request.*;
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
//        router.put("ec", () -> new ListAllOrdersCSVReport());
        setRoute("es", ListAllOrdersScreenReport::new);
//        router.put("fc", () -> new ListOrdersForCustomerCSVReport());
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
