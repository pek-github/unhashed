package org.pek.unit.myset;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.foreign.Box;
import org.pek.structures.MySet;
import org.pek.unit.myset.util.Generator;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MySetAddTest {

    @Test
    public void test01AddOneElement() {
        MySet<Box> set = new MySet<>(Generator.hashByName());
        Box box = Generator.makeBoxes(1).get(0);

        Boolean result = set.add(box);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 1", 1, set.size());
        assertTrue("must hold: result was true", result);
        assertTrue("must hold: box in Set", set.contains(box));
    }

    @Test
    public void test02AddFiveElementsOneByOne() {
        MySet<Box> set = new MySet<>(Generator.hashByName());
        List<Box> boxes = Generator.makeBoxes(5);

        Box box0 = boxes.get(0);
        Box box1 = boxes.get(1);
        Box box2 = boxes.get(2);
        Box box3 = boxes.get(3);
        Box box4 = boxes.get(4);

        Boolean result0 = set.add(box0);
        Boolean result1 = set.add(box1);
        Boolean result2 = set.add(box2);
        Boolean result3 = set.add(box3);
        Boolean result4 = set.add(box4);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());

        assertTrue("must hold: result0 was true", result0);
        assertTrue("must hold: result1 was true", result1);
        assertTrue("must hold: result2 was true", result2);
        assertTrue("must hold: result3 was true", result3);
        assertTrue("must hold: result4 was true", result4);

        assertTrue("must hold: box0 in Set", set.contains(box0));
        assertTrue("must hold: box1 in Set", set.contains(box1));
        assertTrue("must hold: box2 in Set", set.contains(box2));
        assertTrue("must hold: box3 in Set", set.contains(box3));
        assertTrue("must hold: box4 in Set", set.contains(box4));
    }

    @Test
    public void test03AddOneElementMultipleTimes() {
        MySet<Box> set = new MySet<>(Generator.hashByName());
        Box box = Generator.makeBoxes(1).get(0);

        Boolean result0 = set.add(box);
        Boolean result1 = set.add(box);
        Boolean result2 = set.add(box);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 1", 1, set.size());

        assertTrue("must hold: result0 was true", result0);
        assertFalse("must hold: result1 was not true", result1);
        assertFalse("must hold: result2 was not true", result2);

        assertTrue("must hold: box in Set", set.contains(box));
    }

    // -----------------------------------------

    @Test
    public void test05AddAllFiveElements() {
        MySet<Box> set = new MySet<>(Generator.hashByName());
        List<Box> boxes = Generator.makeBoxes(5);

        Boolean result = set.addAll(boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());
        assertTrue("must hold: result was true", result);

        assertTrue("must hold: box0 in Set", set.contains(boxes.get(0)));
        assertTrue("must hold: box1 in Set", set.contains(boxes.get(1)));
        assertTrue("must hold: box2 in Set", set.contains(boxes.get(2)));
        assertTrue("must hold: box3 in Set", set.contains(boxes.get(3)));
        assertTrue("must hold: box4 in Set", set.contains(boxes.get(4)));
    }

    @Test
    public void test06AddOneElementAndThenAddAllTwoElements() {
        MySet<Box> set = new MySet<>(Generator.hashByName());
        Box box = Generator.makeBoxes(1).get(0);
        List<Box> boxes = Generator.makeBoxes(2);

        Boolean result0 = set.add(box);
        Boolean result1 = set.addAll(boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 2", 2, set.size());

        assertTrue("must hold: result0 was true", result0);
        assertTrue("must hold: result1 was true", result1);

        assertTrue("must hold: box0 in Set", set.contains(boxes.get(0)));
        assertTrue("must hold: box1 in Set", set.contains(boxes.get(1)));
    }

    @Test
    public void test07AddOneElementNull() {
        MySet<Box> set = new MySet<>(Generator.hashByName());
        Box box = Generator.makeBoxes(1).get(0);
        List<Box> boxes = Generator.makeBoxes(2);

        Boolean result0 = set.add(box);
        Boolean result1 = set.add(null);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 2", 2, set.size());

        assertTrue("must hold: result0 was true", result0);
        assertTrue("must hold: result1 was true", result1);

        assertTrue("must hold: box0 in Set", set.contains(boxes.get(0)));
        assertTrue("must hold: null in Set", set.contains(null));
    }

}
