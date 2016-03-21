package org.pek.unit.myset;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.foreign.Box;
import org.pek.structures.MySet;
import org.pek.unit.myset.util.Generator;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MySetConstructorsTest {

    @Test
    public void test01SimplestConstructorAndSize() {
        MySet<Box> set = new MySet<>(Generator.hashByName());

        assertTrue("must hold: Set is empty", set.isEmpty());
        assertEquals("must hold: Set size is 0", 0, set.size());
    }

    @Test(expected = NullPointerException.class)
    public void test02SimplestConstructorNegative() {
        new MySet<>(null);
    }

    // --------------------

    @Test
    public void test03SimpleConstructorWithHashMapAndSize() {
        MySet<Box> set =
            new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new);

        assertTrue("must hold: Set is empty", set.isEmpty());
        assertEquals("must hold: Set size is 0", 0, set.size());
    }

    @Test
    public void test04SimpleConstructorWithLinkedHashMapAndSize() {
        MySet<Box> set =
            new MySet<>(Generator.hashByName(), LinkedHashMap<Integer, Box>::new);

        assertTrue("must hold: Set is empty", set.isEmpty());
        assertEquals("must hold: Set size is 0", 0, set.size());
    }

    @Test
    public void test05SimpleConstructorWithTreeMapAndSize() {
        MySet<Box> set =
            new MySet<>(Generator.hashByName(), TreeMap<Integer, Box>::new);

        assertTrue("must hold: Set is empty", set.isEmpty());
        assertEquals("must hold: Set size is 0", 0, set.size());
    }

    @Test(expected = NullPointerException.class)
    public void test06SimpleConstructorNegative() {
        new MySet<>(Generator.hashByName(), null);
    }

    // --------------------

    @Test
    public void test07ConstructorFromSet() {
        List<Box> firstBoxes = Generator.makeBoxes(5);
        Set<Box> boxes = new HashSet<>(firstBoxes);
        MySet<Box> set =
            new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());

        Iterator<Box> iter = boxes.iterator();

        while (iter.hasNext()) {
            Box b = iter.next();
            assertTrue("must hold: boxes(i) in Set", set.contains(b));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test08ConstructorFailsForDuplicateElements() {
        List<Box> boxes = Generator.makeBoxes(5);
        List<Box> firstBoxAgain = Generator.makeBoxes(1);
        boxes.addAll(firstBoxAgain);

        new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);
    }
}
