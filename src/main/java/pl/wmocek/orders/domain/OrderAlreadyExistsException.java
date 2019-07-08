package pl.wmocek.orders.domain;

public class OrderAlreadyExistsException extends Throwable {
    private final CustomerId customerId;
    private final RequestId requestId;

    public OrderAlreadyExistsException(CustomerId customerId, RequestId requestId) {
        this.customerId = customerId;
        this.requestId = requestId;
    }

    public String getCustomerId() {
        return customerId.toString();
    }

    public RequestId getRequestId() {
        return requestId;
    }
}
