public class ApiClient {
    private final Throttler throttler;
    private final ThrottleRules rules;

    public ApiClient(Throttler throttler, ThrottleRules rules) {
        this.throttler = throttler;
        this.rules = rules;
    }

    public void processPayload(String userId, boolean pingRemote) {
        System.out.println("\n-> Initializing payload for: " + userId);

        if (!pingRemote) {
            System.out.println("Local processing only. Success.");
            return;
        }

        if (throttler.canProceed(userId, rules)) {
            System.out.println("[OK] Throttler passed. Executing remote call...");
        } else {
            System.out.println("[DENIED] 429 Too Many Requests. Throttled.");
        }
    }
}
