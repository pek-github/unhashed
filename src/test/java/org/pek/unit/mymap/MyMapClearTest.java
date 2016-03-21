package org.pek.unit.mymap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.foreign.Box;
import org.pek.structures.MyMap;
import org.pek.unit.mymap.util.Generator;
import org.pek.unit.mymap.util.Thing;

import java.util.AbstractMap;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyMapClearTest {

    @Test
    public void test01ClearWhenEmpty() {
        MyMap<Box, Thing> map = new MyMap<>(Generator.hashByName());

        assertTrue("must hold: Map is empty", map.isEmpty());
        assertEquals("must hold: Map size is 0", 0, map.size());

        map.clear();

        assertTrue("must hold: Map is empty", map.isEmpty());
        assertEquals("must hold: Map size is 0", 0, map.size());
    }

    @Test
    public void test02ClearWhenFull() {
        MyMap<Box, Thing> map = new MyMap<>(Generator.hashByName());

        AbstractMap.Entry<Box, Thing> pair = Generator.makePairs(1).get(0);

        Thing old = map.put(pair.getKey(), pair.getValue());

        assertFalse("must hold: Map is not empty", map.isEmpty());
        assertEquals("must hold: Map size is 1", 1, map.size());
        assertTrue("must hold: old value was null", old == null);
        assertTrue("must hold: box in Map", map.containsKey(pair.getKey()));

        map.clear();

        assertTrue("must hold: Map is empty", map.isEmpty());
        assertEquals("must hold: Map size is 0", 0, map.size());
        assertFalse("must hold: box in Map", map.containsKey(pair.getKey()));
    }

}
