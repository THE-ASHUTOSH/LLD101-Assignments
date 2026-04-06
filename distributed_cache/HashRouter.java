package distributed_cache;

import java.util.List;

public class HashRouter implements ShardRouter {
    @Override
    public MemoryShard resolve(List<MemoryShard> shards, String key) {
        if (shards == null || shards.isEmpty()) {
            throw new IllegalArgumentException("No available shards.");
        }
        int bucketIndex = Math.abs(key.hashCode()) % shards.size();
        return shards.get(bucketIndex);
    }
}
