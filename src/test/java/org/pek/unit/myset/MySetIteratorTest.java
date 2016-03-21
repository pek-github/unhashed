package org.pek.unit.myset;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.foreign.Box;
import org.pek.structures.MySet;
import org.pek.unit.myset.util.Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MySetIteratorTest {

    @Test
    public void test01IteratorTraditionally() {
        List<Box> boxes = Generator.makeBoxes(3);
        boxes.add(null);

        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 4", 4, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        List<Box> takenBoxes = extractBoxes(set, true);

        assertEquals("must hold: takenBoxes size is 4", 4, takenBoxes.size());

        for (Box b : takenBoxes) {
            String msg = "must hold: Box " + b + " in boxes";
            assertTrue(msg, boxes.contains(b));
        }
    }

    @Test
    public void test02Iterator() {
        List<Box> boxes = Generator.makeBoxes(3);
        boxes.add(null);

        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 4", 4, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        List<Box> takenBoxes = extractBoxes(set, false);

        assertEquals("must hold: takenBoxes size is 4", 4, takenBoxes.size());

        for (Box b : takenBoxes) {
            String msg = "must hold: Box " + b + " in boxes";
            assertTrue(msg, boxes.contains(b));
        }
    }

    private List<Box> extractBoxes (final MySet<Box> set, Boolean traditionally) {
        List<Box> boxes = new ArrayList<>();

        if (traditionally) {
            Iterator<Box> it = set.iterator();

            while (it.hasNext()) {
                boxes.add(it.next());
            }
        } else {
            for (Box b : set) {
                boxes.add(b);
            }
        }

        return boxes;
    }
}
