package org.pek.structures;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class MyMap<K, V> implements Map<K, V> {

    private final static IllegalStateException NIY =
            new IllegalStateException("METHOD NOT IMPLEMENT YET - TODO");

    private Function<K, Integer> hasher;
    private Map<Integer, AbstractMap.Entry<K, V>> data;

    public MyMap (Function<K, Integer> h) {
        this (h, HashMap::new);
    }

    public MyMap (Function<K, Integer> h,
                  Supplier<Map<Integer, AbstractMap.Entry<K, V>>> map) {

        mandatory(h, Function.class);
        mandatory(map, Supplier.class);

        hasher = h;
        data = map.get();
    }

    private void mandatory (Object o, Class<?> klass) {
        String msg = "Cannot be null: " + klass.getCanonicalName();
        Objects.requireNonNull(o, msg);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean containsKey (Object key) {
        Integer keyHash = calcKeyHash(key);
        return data.containsKey(keyHash);
    }

    private Integer calcKeyHash (Object key) {
        try {
            @SuppressWarnings("unchecked")
            K k = (K) key;

            return hasher.apply(k);
        } catch (Throwable t) {
            String msg = "Cannot calculate hash for key-object: " + key;
            throw new IllegalArgumentException(msg);
        }
    }

    @Override
    public boolean containsValue (Object value) {
        if (true) throw NIY;
        return false;
    }

    @Override
    public V get (Object key) {
        Integer keyHash = calcKeyHash(key);
        boolean found = data.containsKey(keyHash);

        return (found) ? data.get(keyHash).getValue() : null;
    }

    @Override
    public V put (K key, V value) {
        Integer keyHash = calcKeyHash(key);
        AbstractMap.Entry<K, V> entry =
            new AbstractMap.SimpleImmutableEntry<>(key, value);

        AbstractMap.Entry<K, V> old = data.put(keyHash, entry);
        return (old == null) ? null : old.getValue();
    }

    @Override
    public V remove (Object key) {
        if (true) throw NIY;
        return null;
    }

    @Override
    public void putAll (Map<? extends K, ? extends V> m) {
        throw NIY;
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public Set<K> keySet() {
        if (true) throw NIY;
        return null;
    }

    @Override
    public Collection<V> values() {
        if (true) throw NIY;
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        if (true) throw NIY;
        return null;
    }
}
