package pl.wmocek.orders.application.report;

/**
 * ValueReport class for report that contains just a title and one value
 */
public class ValueReport implements Report {
    private final String title;
    private final String value;

    public ValueReport(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }
}