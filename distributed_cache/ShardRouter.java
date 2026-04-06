package distributed_cache;

import java.util.List;

public interface ShardRouter {
    MemoryShard resolve(List<MemoryShard> shards, String key);
}
