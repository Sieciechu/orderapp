package pl.nstrefa.wojciechmocek.domain;

public class OrderAlreadyExistsException extends Throwable {
    private final ClientId clientId;
    private final long requestId;

    public OrderAlreadyExistsException(ClientId clientId, long requestId) {
        this.clientId = clientId;
        this.requestId = requestId;
    }

    public String getClientId() {
        return clientId.toString();
    }

    public long getRequestId() {
        return requestId;
    }
}
