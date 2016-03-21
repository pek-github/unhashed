package org.pek.unit.myset;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.foreign.Box;
import org.pek.structures.MySet;
import org.pek.unit.myset.util.Generator;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MySetBasicTest {

    @Test
    public void test01ConstructorAndSize() {
        List<Box> boxes = Generator.makeBoxes(0);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertTrue("must hold: Set is empty", set.isEmpty());
        assertEquals("must hold: Set size is 0", 0, set.size());
    }

    @Test
    public void test02ConstructorAndContainsAndSizeCaseSimple() {
        List<Box> boxes = Generator.makeBoxes(1);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 1", 1, set.size());
        assertTrue("must hold: boxes(0) in Set", set.contains(boxes.get(0)));
    }

    @Test
    public void test03ConstructorAndContainsAndSizeCaseComplex() {
        List<Box> boxes = Generator.makeBoxes(5);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());

        for (int i = 0; i < 5; i++) {
            assertTrue("must hold: boxes(i) in Set", set.contains(boxes.get(i)));
        }
    }

    @Test(expected = NullPointerException.class)
    public void test04ConstructorNegativeFirstArg() {
        new MySet<>(null, HashMap<Integer, Box>::new, Generator.makeBoxes(1));
    }

    @Test(expected = NullPointerException.class)
    public void test05ConstructorNegativeSecondArg() {
        new MySet<>(Generator.hashByName(), null, Generator.makeBoxes(1));
    }

    @Test(expected = NullPointerException.class)
    public void test06ConstructorNegativeThirdArg() {
        new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, null);
    }

}
