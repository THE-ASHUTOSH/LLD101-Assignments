package distributed_cache;

import java.util.LinkedHashMap;

public class LeastRecentlyUsedAlgo<K> implements ReplacementAlgorithm<K> {
    // Using Java's built-in access-order LinkedHashMap for an elegant, ultra-simple LRU logic
    private final LinkedHashMap<K, Boolean> tracker = new LinkedHashMap<>(16, 0.75f, true);

    @Override
    public void recordAccess(K key) {
        tracker.put(key, Boolean.TRUE);
    }

    @Override
    public K determineVictim() {
        if (tracker.isEmpty()) return null;
        K victim = tracker.keySet().iterator().next();
        tracker.remove(victim);
        return victim;
    }
}
