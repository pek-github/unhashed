package org.pek.unit.myset;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.foreign.Box;
import org.pek.structures.MySet;
import org.pek.unit.myset.util.Generator;

import java.util.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MySetRetainTest {

    @Test
    public void test01RetainManyElements() {
        List<Box> boxes = Generator.makeBoxes(5);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        List<Box> remaining = Arrays.asList(boxes.get(0), boxes.get(2), boxes.get(4));
        Boolean result = set.retainAll(remaining);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 3", 3, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(remaining));
        assertTrue("must hold: retain result is true", result);
    }

    @Test
    public void test02RetainManyElementsEvenNonExistingOnes() {
        List<Box> boxes = Generator.makeBoxes(5);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        List<Box> extra = Generator.makeBoxes(10).subList(8, 9);
        List<Box> remaining = new ArrayList<>(Arrays.asList(boxes.get(0), boxes.get(2), boxes.get(4)));

        List<Box> asked = new ArrayList<>(remaining);
        asked.addAll(extra);
        Boolean result = set.retainAll(asked);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 3", 3, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(remaining));
        assertTrue("must hold: retain result is true", result);
    }

    @Test
    public void test03RetainManyElementsEvenNull() {
        List<Box> boxes = Generator.makeBoxes(5);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);
        set.add(null);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 6", 6, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));
        assertTrue("must hold: null in Set", set.contains(null));

        List<Box> extra = Generator.makeBoxes(10).subList(8, 9);
        List<Box> remaining = new ArrayList<>(Arrays.asList(boxes.get(0), boxes.get(2), boxes.get(4), null));

        List<Box> asked = new ArrayList<>(remaining);
        asked.addAll(extra);
        Boolean result = set.retainAll(asked);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 4", 4, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(remaining));
        assertTrue("must hold: retain result is true", result);
    }

    @Test
    public void test04RetainManyElementsEvenIrrelevantOnes() {
        List<Box> boxes = Generator.makeBoxes(3);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 3", 3, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        Box box0 = boxes.get(0);
        List<Box> remaining = new ArrayList<>(Arrays.asList(box0));
        List<?> asked = new ArrayList<>(Arrays.asList(box0, 0));

        Boolean result = null;
        Boolean illegalArgumentException = false;

        try {
            result = set.retainAll(asked);
        } catch (Throwable t) {
            IllegalArgumentException iae = new IllegalArgumentException();
            illegalArgumentException = (t.getClass() == iae.getClass());
        } finally {
            assertFalse("must hold: Set is not empty", set.isEmpty());
            assertEquals("must hold: Set size is 3", 3, set.size());
            assertTrue("must hold: boxes in Set", set.containsAll(remaining));
            assertNull("must hold: removal result is (still) null", result);
            assertTrue("must hold: IllegalArgumentException detected", illegalArgumentException);
        }
    }

}
