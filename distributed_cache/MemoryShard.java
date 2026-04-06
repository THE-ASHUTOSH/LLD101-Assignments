package distributed_cache;

import java.util.HashMap;
import java.util.Map;

public class MemoryShard {
    private final String shardId;
    private final int maxItems;
    private final Map<String, String> data;
    private final ReplacementAlgorithm<String> algorithm;

    public MemoryShard(String shardId, int maxItems, ReplacementAlgorithm<String> algorithm) {
        this.shardId = shardId;
        this.maxItems = maxItems;
        this.data = new HashMap<>();
        this.algorithm = algorithm;
    }

    public String getShardId() { return shardId; }

    public String fetch(String key) {
        if (data.containsKey(key)) {
            algorithm.recordAccess(key);
            return data.get(key);
        }
        return null;
    }

    public void store(String key, String value) {
        // Update existing
        if (data.containsKey(key)) {
            data.put(key, value);
            algorithm.recordAccess(key);
            return;
        }

        // Evict if full
        if (data.size() >= maxItems) {
            String victim = algorithm.determineVictim();
            if (victim != null) {
                data.remove(victim);
                System.out.println("-> Shard [" + shardId + "] removed key: " + victim + " (Capacity full)");
            }
        }

        // Store new
        data.put(key, value);
        algorithm.recordAccess(key);
    }
}
