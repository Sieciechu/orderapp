package pl.wmocek.orders.application.report.request;


import lombok.NonNull;

public interface Request {

    /**
     * Returns data from the request
     * @param key String
     * @return String|null
     */
    String getData(@NonNull String key);
}
