package org.tsui.twowaymap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Two-way key value store, get value by key or get key by value.
 * The premise is to ensure that the keys and values are unique.
 * @author tsuixh
 * @version 1.0
 */
public class TwoWayMap<K, V> implements Map<K, V>{

    private static final int INITIAL_CAPACITY = 16;

    private Map<K, V> keyValue = new HashMap<>(INITIAL_CAPACITY);
    private Map<V, K> valueKey = new HashMap<>(INITIAL_CAPACITY);

    @Override
    public int size() {
        return keyValue.size();
    }

    @Override
    public boolean isEmpty() {
        return keyValue.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return keyValue.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return keyValue.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return keyValue.get(key);
    }

    @Override
    public V put(K key, V value) {
        keyValue.put(key, value);
        valueKey.put(value, key);
        return value;
    }

    @Override
    public V remove(Object key) {
        V value = keyValue.remove(key);
        valueKey.remove(value);
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Set<? extends Entry<? extends K, ? extends V>> entries = m.entrySet();
        entries.forEach((Consumer<Entry<? extends K, ? extends V>>) entry -> {
            put(entry.getKey(), entry.getValue());
        });
    }

    @Override
    public void clear() {
        keyValue.clear();
        valueKey.clear();
    }

    @Override
    public Set<K> keySet() {
        return keyValue.keySet();
    }

    @Override
    public Collection<V> values() {
        return keyValue.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return keyValue.entrySet();
    }

    public K getKeyByValue(V value) {
        return valueKey.get(value);
    }

}
