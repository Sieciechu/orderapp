package pl.wmocek.orders.domain;

public class OrderAlreadyExistsException extends Throwable {
    private final ClientId clientId;
    private final RequestId requestId;

    public OrderAlreadyExistsException(ClientId clientId, RequestId requestId) {
        this.clientId = clientId;
        this.requestId = requestId;
    }

    public String getClientId() {
        return clientId.toString();
    }

    public RequestId getRequestId() {
        return requestId;
    }
}
