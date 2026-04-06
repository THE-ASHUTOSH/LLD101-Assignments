package distributed_cache;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Booting Simple Distributed Cache ---");

        PersistentStore database = new FakeStore();
        
        MemoryShard shard1 = new MemoryShard("Shard-X", 2, new LeastRecentlyUsedAlgo<>());
        MemoryShard shard2 = new MemoryShard("Shard-Y", 2, new LeastRecentlyUsedAlgo<>());
        MemoryShard shard3 = new MemoryShard("Shard-Z", 2, new LeastRecentlyUsedAlgo<>());

        CacheCluster cluster = new CacheCluster(List.of(shard1, shard2, shard3), new HashRouter(), database);

        cluster.persist("item_1", "Apple");
        cluster.persist("item_2", "Banana");
        cluster.persist("item_3", "Cherry");

        System.out.println("\n--- Testing Retrieval ---");
        cluster.retrieve("item_1"); 
        cluster.retrieve("item_404"); 

        System.out.println("\n--- Testing Eviction ---");
        cluster.persist("item_4", "Date");
        cluster.persist("item_5", "Elderberry");
        cluster.persist("item_6", "Fig"); 
        
        cluster.retrieve("item_1");
    }
}
