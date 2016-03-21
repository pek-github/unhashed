package org.pek.unit.mymap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.foreign.Box;
import org.pek.structures.MyMap;
import org.pek.unit.mymap.util.Generator;
import org.pek.unit.mymap.util.Thing;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyMapConstructorTest {

    @Test
    public void test01SimpleConstructorAndSize() {
        MyMap<Box, Thing> map = new MyMap<>(Generator.hashByName());

        assertTrue("must hold: Map is empty", map.isEmpty());
        assertEquals("must hold: Map size is 0", 0, map.size());
    }

    @Test(expected = NullPointerException.class)
    public void test02SimpleConstructorNegative() {
        new MyMap<>(null);
    }

    @Test
    public void test03ConstructorWithHashMapAndSize() {
        MyMap<Box, Thing> map =
            new MyMap<>(Generator.hashByName(),
                        HashMap<Integer, AbstractMap.Entry<Box, Thing>>::new);

        assertTrue("must hold: Map is empty", map.isEmpty());
        assertEquals("must hold: Map size is 0", 0, map.size());
    }

    @Test
    public void test04ConstructorWithLinkedMapAndSize() {
        MyMap<Box, Thing> map =
            new MyMap<>(Generator.hashByName(),
                        LinkedHashMap<Integer, AbstractMap.Entry<Box, Thing>>::new);

        assertTrue("must hold: Map is empty", map.isEmpty());
        assertEquals("must hold: Map size is 0", 0, map.size());
    }

    @Test
    public void test05ConstructorWithTreeMapAndSize() {
        MyMap<Box, Thing> map =
            new MyMap<>(Generator.hashByName(),
                        TreeMap<Integer, AbstractMap.Entry<Box, Thing>>::new);

        assertTrue("must hold: Map is empty", map.isEmpty());
        assertEquals("must hold: Map size is 0", 0, map.size());
    }

    @Test(expected = NullPointerException.class)
    public void test06ConstructorNegative() {
        new MyMap<>(Generator.hashByName(), null);
    }

}
