public class ThrottleRules {
    private final int limit;
    private final long windowMs;

    public ThrottleRules(int limit, long windowMs) {
        this.limit = limit;
        this.windowMs = windowMs;
    }

    public int getLimit() { return limit; }
    public long getWindowMs() { return windowMs; }
}
