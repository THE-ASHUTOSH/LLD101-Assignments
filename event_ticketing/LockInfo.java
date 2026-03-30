package event_ticketing;

public class LockInfo {
    private final String customerId;
    private final long expirationTime;

    public LockInfo(String customerId, long expirationTime) {
        this.customerId = customerId;
        this.expirationTime = expirationTime;
    }

    public String getCustomerId() { return customerId; }
    public boolean isExpired() {
        return System.currentTimeMillis() > expirationTime;
    }
}
