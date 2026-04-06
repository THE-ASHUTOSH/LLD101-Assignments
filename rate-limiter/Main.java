public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Allows 3 calls per 1 second
        ThrottleRules standardRule = new ThrottleRules(3, 1000);
        
        Throttler engine = new Throttler(new StaticWindowAlgorithm());
        ApiClient client = new ApiClient(engine, standardRule);

        String user = "ClientX";

        System.out.println("=== Testing STATIC Window ===");
        client.processPayload(user, true); // 1 OK
        client.processPayload(user, true); // 2 OK
        client.processPayload(user, false); // Local (Ignore limit)
        client.processPayload(user, true); // 3 OK
        client.processPayload(user, true); // 4 DENIED

        Thread.sleep(1100); 
        
        client.processPayload(user, true); // 1 OK (New Window)

        System.out.println("\n=== Testing ROLLING Log ===");
        
        engine.switchAlgorithm(new RollingLogAlgorithm());
        
        client.processPayload(user, true);  // 1 OK
        client.processPayload(user, true);  // 2 OK
        client.processPayload(user, true);  // 3 OK
        client.processPayload(user, true);  // 4 DENIED
    }
}
