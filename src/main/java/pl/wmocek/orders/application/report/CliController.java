package pl.wmocek.orders.application.report;

import pl.wmocek.orders.application.report.request.*;
import pl.wmocek.orders.domain.DistinctCustomersRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * CliController translates CLI input to Report Requests
 */
public class CliController implements Controller {

    private final String DEFAULT_REPORTS_DIR_PATH = "src/main/resources/";

    private DistinctCustomersRepository repository;
    private Scanner scanner;

    /**
     * Maps menu options to request factory.
     *  It works as a kind of simple router: from chosen user option to Report Request
     */
    private Map< String, RequestFactory> router = new HashMap<>();

    /**
     * CliController constructor has already defined default router mapping
     * @param repository
     * @param scanner
     */
    CliController(DistinctCustomersRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;

        setRoute("a", CountAllOrders::new);
        setRoute("b", CountAllOrdersForCustomer::new);
        setRoute("c", TotalPriceOfAllOrders::new);
        setRoute("d", TotalPriceOfAllOrdersForCustomer::new);
        setRoute("e", ListAllOrders::new);
        setRoute("f", ListAllOrdersForCustomer::new);
        setRoute("g", AveragePriceOfAllOrders::new);
        setRoute("h", AveragePriceOfAllOrdersForCustomer::new);

    }

    /**
     * Support method for defining routes
     * @param key
     * @param factory
     */
    private void setRoute(String key, RequestFactory factory) {
        router.put(key, factory);
    }

    /**
     * Prints the menus, asks a user for options and then creates the right Request
     * @return Request|null Null if factory cannot be created or user chose to quit.
     */
    public Request getRequest(){

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
        if ("f".equals(chosenOption) || "e".equals(chosenOption)) {
            commandData.put("offset", "0");
            commandData.put("limit", "50");
        }

        String reportDestination = chooseDestination();
        commandData.put("destination", reportDestination);
        if ("file".equals(reportDestination)) {

            String reportFileName = "report" + LocalDateTime.now();
            commandData.put("fileName", DEFAULT_REPORTS_DIR_PATH + reportFileName);
        }

        RequestFactory requestFactory = router.get(chosenOption);
        if (null == requestFactory) {
            return null;
        }

        return requestFactory.create(commandData);
    }

    private String chooseDestination() {
        printReportDestination();
        String defaultDestination = "screen";
        String destination = scanner.nextLine();
        if ("c".equals(destination)) {
            return "file";
        }
        return defaultDestination;
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
