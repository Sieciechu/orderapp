package pl.wmocek.orders.application.report;

import pl.wmocek.orders.domain.DistinctCustomersRepository;

import java.util.Scanner;

public class CliControllerFactory {

    private DistinctCustomersRepository repository;
    private Scanner scanner;

    public CliControllerFactory(DistinctCustomersRepository repository) {
        this.repository = repository;
        this.scanner = new Scanner(System.in);
    }

    public CliController create() {
        return new CliController(repository, scanner);
    }
}
