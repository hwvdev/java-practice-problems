import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public final class CacheTTL {
    private final Map<String, ValueObj> concurrHashMap = new ConcurrentHashMap<>();
    private static final long TTL_MS = 1000 * 60 * 10;

    private static class ValueObj {
        final String value;
        final long timestamp;

        public ValueObj(String value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    public void put(String key, String value) {
        concurrHashMap.putIfAbsent(key, new ValueObj(value, System.currentTimeMillis()));
    }

    public String get(String key) {
        ValueObj valueObj = concurrHashMap.compute(key, (k, v) -> {
            if (v == null) return null;
            if (System.currentTimeMillis() - v.timestamp > TTL_MS) {
                return null;
            }
            return new ValueObj(v.value, System.currentTimeMillis());
        });
        return valueObj == null ? null : valueObj.value;
    }

    public void cleanup() {
        long timestmp = System.currentTimeMillis();
        concurrHashMap.entrySet().parallelStream()
                .forEach(ty -> {
                    concurrHashMap.compute(ty.getKey(), (k, v) -> {
                        if (v == null) return null;
                        if (timestmp - v.timestamp > TTL_MS) {
                            return null;
                        }
                        return v;
                    });
                });
    }

    public static void main(String[] args) {


    }

     class CounterStore {
        private final ConcurrentMap<String, LongAdder> chm;

        public CounterStore() {
            this.chm = new ConcurrentHashMap<>();
        }

        void increment(String key) {
            chm.computeIfAbsent(key, a -> new LongAdder()).increment();
        }

        int get(String key){
            return chm.get(key).intValue();
        }
    }



}
