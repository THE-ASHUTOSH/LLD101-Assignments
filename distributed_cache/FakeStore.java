package distributed_cache;

import java.util.HashMap;
import java.util.Map;

public class FakeStore implements PersistentStore {
    private final Map<String, String> disk = new HashMap<>();

    @Override
    public String load(String key) {
        System.out.println("[DB] Loading from disk for key: " + key);
        return disk.get(key);
    }

    @Override
    public void save(String key, String value) {
        System.out.println("[DB] Saving to disk: " + key + " -> " + value);
        disk.put(key, value);
    }
}
