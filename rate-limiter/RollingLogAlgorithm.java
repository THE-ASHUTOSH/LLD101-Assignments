import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RollingLogAlgorithm implements ThrottleAlgorithm {
    private final ConcurrentHashMap<String, Queue<Long>> logs = new ConcurrentHashMap<>();

    @Override
    public boolean evaluate(String identifier, ThrottleRules rules) {
        long now = System.currentTimeMillis();
        long cutoff = now - rules.getWindowMs();

        Queue<Long> timestamps = logs.computeIfAbsent(identifier, id -> new ConcurrentLinkedQueue<>());

        // Evict expired entries
        while (!timestamps.isEmpty() && timestamps.peek() <= cutoff) {
            timestamps.poll();
        }

        if (timestamps.size() < rules.getLimit()) {
            timestamps.offer(now);
            return true;
        }

        return false;
    }
}
