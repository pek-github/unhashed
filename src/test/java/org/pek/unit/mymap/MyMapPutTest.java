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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyMapPutTest {

    @Test
    public void test01PutOnePair() {
        MyMap<Box, Thing> map = new MyMap<>(Generator.hashByName());
        AbstractMap.Entry<Box, Thing> pair = Generator.makePairs(1).get(0);

        Thing old = map.put(pair.getKey(), pair.getValue());

        assertFalse("must hold: Map is not empty", map.isEmpty());
        assertEquals("must hold: Map size is 1", 1, map.size());
        assertTrue("must hold: old value was null", old == null);
        assertTrue("must hold: box in Map", map.containsKey(pair.getKey()));
    }

    @Test
    public void test02PutFivePairsOneByOne() {
        MyMap<Box, Thing> map = new MyMap<>(Generator.hashByName());
        List<AbstractMap.Entry<Box, Thing>> pairs = Generator.makePairs(5);

        Thing old0 = map.put(pairs.get(0).getKey(), pairs.get(0).getValue());
        Thing old1 = map.put(pairs.get(1).getKey(), pairs.get(1).getValue());
        Thing old2 = map.put(pairs.get(2).getKey(), pairs.get(2).getValue());
        Thing old3 = map.put(pairs.get(3).getKey(), pairs.get(3).getValue());
        Thing old4 = map.put(pairs.get(4).getKey(), pairs.get(4).getValue());

        assertFalse("must hold: Map is not empty", map.isEmpty());
        assertEquals("must hold: Map size is 5", 5, map.size());

        assertTrue("must hold: old0 value was null", old0 == null);
        assertTrue("must hold: old1 value was null", old1 == null);
        assertTrue("must hold: old2 value was null", old2 == null);
        assertTrue("must hold: old3 value was null", old3 == null);
        assertTrue("must hold: old4 value was null", old4 == null);

        assertTrue("must hold: box0 in Map", map.containsKey(pairs.get(0).getKey()));
        assertTrue("must hold: box1 in Map", map.containsKey(pairs.get(1).getKey()));
        assertTrue("must hold: box2 in Map", map.containsKey(pairs.get(2).getKey()));
        assertTrue("must hold: box3 in Map", map.containsKey(pairs.get(3).getKey()));
        assertTrue("must hold: box4 in Map", map.containsKey(pairs.get(4).getKey()));
    }

    @Test
    public void test03PutOnePairMultipleTimes() {
        MyMap<Box, Thing> map = new MyMap<>(Generator.hashByName());
        AbstractMap.Entry<Box, Thing> pair = Generator.makePairs(1).get(0);

        Thing old0 = map.put(pair.getKey(), pair.getValue());
        Thing old1 = map.put(pair.getKey(), pair.getValue());
        Thing old2 = map.put(pair.getKey(), pair.getValue());

        assertFalse("must hold: Map is not empty", map.isEmpty());
        assertEquals("must hold: Map size is 1", 1, map.size());

        assertTrue("must hold: old0 value was null", old0 == null);
        assertEquals("must hold: old1 value was from old0", "name0_0", old1.getName());
        assertEquals("must hold: old2 value was from old0", "name0_0", old2.getName());
        assertTrue("must hold: old1 is the same as old2", old1 == old2);

        assertTrue("must hold: box in Map", map.containsKey(pair.getKey()));

        Thing current = map.get(pair.getKey());
        assertTrue("must hold: current is the same as old2", current == old2);
        assertEquals("must hold: current value is from old0", pair.getValue().getName(), current.getName());
    }

    @Test
    public void test04PutPairsWithTheSameBoxAsKeyMultipleTimes() {
        MyMap<Box, Thing> map = new MyMap<>(Generator.hashByName());
        List<AbstractMap.Entry<Box, Thing>> pairs = Generator.makePairsOfSameBox(3);

        Thing old0 = map.put(pairs.get(0).getKey(), pairs.get(0).getValue());
        Thing old1 = map.put(pairs.get(1).getKey(), pairs.get(1).getValue());
        Thing old2 = map.put(pairs.get(2).getKey(), pairs.get(2).getValue());

        assertFalse("must hold: Map is not empty", map.isEmpty());
        assertEquals("must hold: Map size is 1", 1, map.size());

        assertTrue("must hold: old0 value was null", old0 == null);
        assertEquals("must hold: old1 value was from old0", "name0_0", old1.getName());
        assertEquals("must hold: old2 value was from old1", "name0_1", old2.getName());

        assertTrue("must hold: box in Map", map.containsKey(pairs.get(2).getKey()));

        AbstractMap.Entry<Box, Thing> pair2 = pairs.get(2);
        Thing current = map.get(pair2.getKey());
        assertEquals("must hold: current value is from pairs.get(2)", pair2.getValue().getName(), current.getName());
    }

    @Test
    public void test05PutOnePairWithNullKey() {
        Thing.reset();
        MyMap<Box, Thing> map = new MyMap<>(Generator.hashByName());
        AbstractMap.Entry<Box, Thing> pair = new AbstractMap.SimpleEntry<>(null, Thing.makeThing("name_null"));

        Thing old = map.put(pair.getKey(), pair.getValue());

        assertFalse("must hold: Map is not empty", map.isEmpty());
        assertEquals("must hold: Map size is 1", 1, map.size());
        assertTrue("must hold: old value was null", old == null);
        assertTrue("must hold: box in Map", map.containsKey(pair.getKey()));
    }

}
