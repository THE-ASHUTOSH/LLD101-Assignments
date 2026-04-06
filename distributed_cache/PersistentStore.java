package distributed_cache;

public interface PersistentStore {
    String load(String key);
    void save(String key, String value);
}
