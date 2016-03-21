package org.pek.structures;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MySet<E> implements Set<E> {

    private Function<E, Integer> hasher;
    private Map<Integer, E> data;

    public MySet (Function<E, Integer> h) {
        this (h, HashMap::new, new ArrayList<>(0));
    }

    public MySet (Function<E, Integer> h, Supplier<Map<Integer, E>> map) {
        this (h, map, new ArrayList<>(0));
    }

    public MySet (Function<E, Integer> h, Supplier<Map<Integer, E>> map, Collection<E> elements) {
        mandatory(h, Function.class);
        mandatory(map, Supplier.class);
        mandatory(elements, Collection.class);

        LinkedHashMap<Integer, E> mapped = convertCollection(h, elements);

        hasher = h;
        data = map.get();
        data.putAll(mapped);
    }

    private void mandatory (Object o, Class<?> klass) {
        String msg = "Cannot be null: " + klass.getCanonicalName();
        Objects.requireNonNull(o, msg);
    }

    private LinkedHashMap<Integer, E> convertCollection (
            Function<E, Integer> h,
            Collection<E> elements) {

        Integer elems = elements.size();
        LinkedHashMap<Integer, E> mapped = new LinkedHashMap<>(elems);

        for (E e : elements) {
            Integer key = h.apply(e);
            mapped.put(key, e);
        }

        guardConversion(elems, mapped.size());

        return mapped;
    }

    private void guardConversion(Integer total, Integer converted) {

        if (!total.equals(converted)) {
            String template =
                "Only %d out of %d collection elements can be unique distinguished";
            String msg = String.format(template, converted, total);
            throw new IllegalArgumentException(msg);
        }
    }

    @Override
    public boolean add (E e) {
        Integer key = hasher.apply(e);

        if (data.containsKey(key)) {
            return false;
        }

        data.put(key, e);
        return true;
    }

    @Override
    public boolean addAll (Collection<? extends E> c) {
        boolean modified = false;

        for (E e : c) {
            if (add(e)) {
                modified = true;
            }
        }

        return modified;
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public boolean contains (Object o) {
        Integer key = this.calcKey(o);

        if (key == null) {
            return false;
        }

        return data.containsKey(key);
    }

    private Integer calcKey (Object o) {
        try {
            @SuppressWarnings("unchecked")
            E e = (E) o;

            return hasher.apply(e);
        } catch (Throwable t) {
            String msg = "Cannot calculate hash for object: " + o;
            throw new IllegalArgumentException(msg);
        }
    }

    @Override
    public boolean containsAll (Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return data.values().iterator();
    }

    @Override
    public boolean remove (Object o) {
        Integer key = this.calcKey(o);

        if (key == null) {
            return false;
        }

        E old = data.remove(key);
        return (old != null);
    }

    @Override
    public boolean removeAll (Collection<?> c) {
        guardRemoveAll(c);

        boolean modified = false;

        for (Object o : c) {
            if (this.remove(o)) {
                modified = true;
            }
        }

        return modified;
    }

    private void guardRemoveAll (Collection<?> c) {
        for (Object o : c) {
            calcKey(o);
        }
    }

    @Override
    public boolean retainAll (Collection<?> c) {
        final Set<Integer> retainables = new HashSet<>(c.size());

        for (Object o : c) {
            Integer key = this.calcKey(o);
            retainables.add(key);
        }

        int initialSize = data.size();

        /* special handling for 'null' needed */
        Set<Integer> keysToNull = keysToNullValue(retainables.contains(this.calcKey(null)));

        data = data.entrySet()
                   .stream()
                   .filter(e -> e.getValue() != null && retainables.contains(e.getKey()))
                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for (Integer key : keysToNull) {
            data.put(key, null);
        }

        int finalSize = data.size();

        return (initialSize > finalSize);
    }

    private Set<Integer> keysToNullValue (Boolean collect) {
        if (collect) {
            return
                data.entrySet()
                    .stream()
                    .filter(e -> e.getValue() == null)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toSet());

        } else {
            return new HashSet<>(0);
        }
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public Object[] toArray() {
        ArrayList<?> list = new ArrayList<>(data.values());
        return list.toArray();
    }

    @Override
    public <T> T[] toArray (T[] a) {
        ArrayList<?> list = new ArrayList<>(data.values());
        return list.toArray(a);
    }

}
