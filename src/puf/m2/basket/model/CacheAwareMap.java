package puf.m2.basket.model;

import java.util.HashMap;

public class CacheAwareMap<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = 2056301907098102257L;

    public V put(K key, V value) {
        if (BasketEntity.isCached()) {
            return super.put(key, value);
        }
        return null;
    }

}