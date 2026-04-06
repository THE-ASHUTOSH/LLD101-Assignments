import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StaticWindowAlgorithm implements ThrottleAlgorithm {
    
    private static class WindowBlock {
        long startTime;
        AtomicInteger count;

        WindowBlock(long startTime) {
            this.startTime = startTime;
            this.count = new AtomicInteger(0);
        }
    }

    private final ConcurrentHashMap<String, WindowBlock> dataLayer = new ConcurrentHashMap<>();

    @Override
    public boolean evaluate(String identifier, ThrottleRules rules) {
        long now = System.currentTimeMillis();
        long windowBoundary = (now / rules.getWindowMs()) * rules.getWindowMs();

        WindowBlock block = dataLayer.compute(identifier, (key, existing) -> {
            if (existing == null || existing.startTime != windowBoundary) {
                return new WindowBlock(windowBoundary);
            }
            return existing;
        });

        return block.count.incrementAndGet() <= rules.getLimit();
    }
}
