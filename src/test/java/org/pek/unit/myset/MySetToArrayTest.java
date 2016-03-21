package org.pek.unit.myset;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.foreign.Box;
import org.pek.structures.MySet;
import org.pek.unit.myset.util.Generator;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MySetToArrayTest {

    @Test
    public void test01ToArrayForObjects() {
        List<Box> boxes = Generator.makeBoxes(3);
        boxes.add(null);

        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 4", 4, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        Object[] objects = set.toArray();

        assertEquals("must hold: Object[] size is 4", 4, objects.length);

        for (Object o : objects) {
            String msg = "must hold: Object " + o + " in boxes";
            assertTrue(msg, boxes.contains(o));
        }
    }

    @Test
    public void test02ToArrayGeneric() {
        List<Box> boxes = Generator.makeBoxes(3);
        boxes.add(null);

        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 4", 4, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        Box[] takenBoxes = set.toArray(new Box[4]);

        assertEquals("must hold: Object[] size is 4", 4, takenBoxes.length);

        for (Box b : takenBoxes) {
            String msg = "must hold: Object " + b + " in boxes";
            assertTrue(msg, boxes.contains(b));
        }
    }

    @Test
    public void test03ToArrayGenericMoreSlots() {
        List<Box> boxes = Generator.makeBoxes(3);
        boxes.add(null);

        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 4", 4, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        Box[] takenBoxes = set.toArray(new Box[6]);

        assertEquals("must hold: Object[] size is 6", 6, takenBoxes.length);

        long nullCounter = Stream.of(takenBoxes)
                                 .filter(b -> b == null)
                                 .count();

        assertEquals("must hold: nullCounter == 3", 3L, nullCounter);

        for (Box b : takenBoxes) {
            String msg = "must hold: Object " + b + " in boxes";
            assertTrue(msg, boxes.contains(b));
        }
    }

    @Test
    public void test04ToArrayGenericLessSlots() {
        List<Box> boxes = Generator.makeBoxes(3);
        boxes.add(null);

        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 4", 4, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        Box[] takenBoxes = set.toArray(new Box[2]);

        assertEquals("must hold: Object[] size is 4", 4, takenBoxes.length);

        for (Box b : takenBoxes) {
            String msg = "must hold: Object " + b + " in boxes";
            assertTrue(msg, boxes.contains(b));
        }
    }

}
