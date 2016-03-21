package org.pek.unit.myset;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.foreign.Box;
import org.pek.structures.MySet;
import org.pek.unit.myset.util.Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MySetContainsTest {

    @Test
    public void test01ContainsWhenExists() {
        List<Box> boxes = Generator.makeBoxes(1);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 1", 1, set.size());
        assertTrue("must hold: box in Set", set.contains(boxes.get(0)));
    }

    @Test
    public void test02ContainsWhenDoesNotExist() {
        List<Box> allBoxes = Generator.makeBoxes(4);
        List<Box> boxes = allBoxes.subList(0, 2);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 2", 2, set.size());

        assertTrue("must hold: box0 in Set", set.contains(allBoxes.get(0)));
        assertTrue("must hold: box1 in Set", set.contains(allBoxes.get(1)));
        assertFalse("must hold: box2 not in Set", set.contains(allBoxes.get(2)));
        assertFalse("must hold: box3 not in Set", set.contains(allBoxes.get(3)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test03ContainsSomethingIrrelevant() {
        List<Box> boxes = Generator.makeBoxes(1);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 1", 1, set.size());
        assertTrue("must hold: box in Set", set.contains(boxes.get(0)));

        assertFalse("must hold: irrelevant integer not in Set", set.contains(0));
    }

    @Test
    public void test04ContainsAllWhenAllExist() {
        List<Box> boxes = Generator.makeBoxes(4);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        List<Box> someBoxes = boxes.subList(0, 2);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 4", 4, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));
        assertTrue("must hold: some boxes in Set", set.containsAll(someBoxes));
    }

    @Test
    public void test05ContainsAllWhenNoneExists() {
        List<Box> allBoxes = Generator.makeBoxes(4);
        List<Box> firstBoxes = allBoxes.subList(0, 2);
        List<Box> lastBoxes = allBoxes.subList(2, 4);

        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, firstBoxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 2", 2, set.size());
        assertTrue("must hold: first boxes in Set", set.containsAll(firstBoxes));
        assertFalse("must hold: last boxes not in Set", set.containsAll(lastBoxes));
    }

    @Test
    public void test06ContainsAllWhenSomeExist() {
        List<Box> allBoxes = Generator.makeBoxes(4);
        List<Box> firstBoxes = allBoxes.subList(0, 2);

        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, firstBoxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 2", 2, set.size());
        assertTrue("must hold: first boxes in Set", set.containsAll(firstBoxes));
        assertFalse("must hold: all boxes not in Set", set.containsAll(allBoxes));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test07ContainsAllSomethingIrrelevant() {
        List<Box> boxes = Generator.makeBoxes(4);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        List<Object> stuff = new ArrayList<>(boxes);
        stuff.add(0);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 4", 4, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));
        assertFalse("must hold: stuff not in Set", set.containsAll(stuff));
    }

}
