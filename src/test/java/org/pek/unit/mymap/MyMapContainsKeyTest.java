package org.pek.unit.mymap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.foreign.Box;
import org.pek.structures.MyMap;
import org.pek.unit.mymap.util.Generator;
import org.pek.unit.mymap.util.Thing;

import java.util.AbstractMap;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyMapContainsKeyTest {

    @Test
    public void test01ContainsKeyWhenExists() {
        MyMap<Box, Thing> map = new MyMap<>(Generator.hashByName());
        AbstractMap.Entry<Box, Thing> pair = Generator.makePairs(1).get(0);

        Thing old = map.put(pair.getKey(), pair.getValue());

        assertFalse("must hold: Map is not empty", map.isEmpty());
        assertEquals("must hold: Map size is 1", 1, map.size());
        assertNull("must hold: old value was null", old);
        assertTrue("must hold: box in Map", map.containsKey(pair.getKey()));
    }

    @Test
    public void test02ContainsKeyWhenDoesNotExist() {
        MyMap<Box, Thing> map = new MyMap<>(Generator.hashByName());
        List<AbstractMap.Entry<Box, Thing>> pairs = Generator.makePairs(2);

        Thing old = map.put(pairs.get(0).getKey(), pairs.get(0).getValue());

        assertFalse("must hold: Map is not empty", map.isEmpty());
        assertEquals("must hold: Map size is 1", 1, map.size());
        assertNull("must hold: old value was null", old);

        assertTrue("must hold: box0 in Map", map.containsKey(pairs.get(0).getKey()));
        assertFalse("must hold: box1 not in Map", map.containsKey(pairs.get(1).getKey()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test03ContainsKeySomethingIrrelevant() {
        MyMap<Box, Thing> map = new MyMap<>(Generator.hashByName());
        AbstractMap.Entry<Box, Thing> pair = Generator.makePairs(1).get(0);

        Thing old = map.put(pair.getKey(), pair.getValue());

        assertFalse("must hold: Map is not empty", map.isEmpty());
        assertEquals("must hold: Map size is 1", 1, map.size());
        assertNull("must hold: old value was null", old);

        assertFalse("must hold: irrelevant integer not in Map", map.containsKey(0));
    }

    @Test
    public void test04ContainsKeyForPairWithNullKey() {
        Thing.reset();
        MyMap<Box, Thing> map = new MyMap<>(Generator.hashByName());
        AbstractMap.Entry<Box, Thing> pair =
            new AbstractMap.SimpleEntry<>(null, Thing.makeThing("name_null"));

        Thing old = map.put(pair.getKey(), pair.getValue());

        assertFalse("must hold: Map is not empty", map.isEmpty());
        assertEquals("must hold: Map size is 1", 1, map.size());
        assertNull("must hold: old value was null", old);
        assertTrue("must hold: box in Map", map.containsKey(pair.getKey()));
    }
}
