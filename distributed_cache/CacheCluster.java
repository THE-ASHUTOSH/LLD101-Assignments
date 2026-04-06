package distributed_cache;

import java.util.List;

public class CacheCluster {
    private final List<MemoryShard> shards;
    private final ShardRouter router;
    private final PersistentStore store;

    public CacheCluster(List<MemoryShard> shards, ShardRouter router, PersistentStore store) {
        this.shards = shards;
        this.router = router;
        this.store = store;
    }

    public String retrieve(String key) {
        MemoryShard shard = router.resolve(shards, key);
        String value = shard.fetch(key);

        if (value != null) {
            System.out.println("HIT in " + shard.getShardId() + " -> " + key);
            return value;
        }

        System.out.println("MISS in " + shard.getShardId() + " -> " + key);
        value = store.load(key);

        if (value != null) {
            shard.store(key, value);
        }

        return value;
    }

    public void persist(String key, String value) {
        store.save(key, value);
        MemoryShard shard = router.resolve(shards, key);
        shard.store(key, value);
        System.out.println("Saved " + key + " in " + shard.getShardId());
    }
}
