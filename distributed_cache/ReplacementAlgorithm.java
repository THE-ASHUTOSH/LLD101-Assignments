package distributed_cache;

public interface ReplacementAlgorithm<K> {
    void recordAccess(K key);
    K determineVictim();
}
