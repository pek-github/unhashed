package org.pek.unit.myset;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.foreign.Box;
import org.pek.structures.MySet;
import org.pek.unit.myset.util.Generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MySetRemoveTest {

    @Test
    public void test01RemoveOneElement() {
        List<Box> boxes = Generator.makeBoxes(5);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        Box removable = boxes.get(4);
        boxes.remove(removable);
        Boolean result = set.remove(removable);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 4", 4, set.size());
        assertFalse("must hold: removed box not in set", set.contains(removable));
        assertTrue("must hold: rest boxes in Set", set.containsAll(boxes));
        assertTrue("must hold: removal result is true", result);
    }

    @Test
    public void test02RemoveManyElementsOneByOne() {
        List<Box> boxes = Generator.makeBoxes(5);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        Box removable0 = boxes.get(0);
        Box removable2 = boxes.get(2);
        Box removable4 = boxes.get(4);

        boxes.remove(removable0);
        boxes.remove(removable2);
        boxes.remove(removable4);

        Boolean result0 = set.remove(removable0);
        Boolean result2 = set.remove(removable2);
        Boolean result4 = set.remove(removable4);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 2", 2, set.size());
        assertFalse("must hold: removed box0 not in set", set.contains(removable0));
        assertFalse("must hold: removed box2 not in set", set.contains(removable2));
        assertFalse("must hold: removed box4 not in set", set.contains(removable4));

        assertTrue("must hold: rest boxes in Set", set.containsAll(boxes));
        assertTrue("must hold: removal result0 is true", result0);
        assertTrue("must hold: removal result2 is true", result2);
        assertTrue("must hold: removal result4 is true", result4);
    }

    @Test
    public void test03RemoveElementThatDoesNotExist() {
        List<Box> boxes = Generator.makeBoxes(3);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 3", 3, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        Box other = Generator.makeBoxes(7).get(6);
        Boolean result = set.remove(other);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 3", 3, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));
        assertFalse("must hold: removal result is false", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test04RemoveIrrelevantElement() {
        List<Box> boxes = Generator.makeBoxes(3);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 3", 3, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        set.remove(0);
    }

    @Test
    public void test05RemoveOneElementTwice() {
        List<Box> boxes = Generator.makeBoxes(5);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        Box removable = boxes.get(4);
        boxes.remove(removable);
        Boolean resultFirst = set.remove(removable);
        Boolean resultSecond = set.remove(removable);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 4", 4, set.size());
        assertFalse("must hold: removed box not in set", set.contains(removable));
        assertTrue("must hold: rest boxes in Set", set.containsAll(boxes));
        assertTrue("must hold: removal result is true", resultFirst);
        assertFalse("must hold: removal result is false", resultSecond);
    }

    // ------------

    @Test
    public void test06RemoveManyElements() {
        List<Box> boxes = Generator.makeBoxes(5);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        List<Box> removables = Arrays.asList(boxes.get(0), boxes.get(2), boxes.get(4));
        List<Box> remaining = Arrays.asList(boxes.get(1), boxes.get(3));

        Boolean result = set.removeAll(removables);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 2", 2, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(remaining));
        assertTrue("must hold: removal result is true", result);
    }

    @Test
    public void test07RemoveManyElementsThatDoNotExist() {
        List<Box> boxes = Generator.makeBoxes(3);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 3", 3, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        List<Box> removables = Generator.makeBoxes(7).subList(5, 7);
        List<Box> remaining = boxes;

        Boolean result = set.removeAll(removables);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 3", 3, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(remaining));
        assertFalse("must hold: removal result is false", result);
    }

    @Test
    public void test08RemoveManyElementsThatOnlySomeExist() {
        List<Box> boxes = Generator.makeBoxes(3);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 3", 3, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        List<Box> removables = Generator.makeBoxes(7).subList(5, 7);
        removables.add(boxes.get(2));
        List<Box> remaining = boxes.subList(0, 2);

        Boolean result = set.removeAll(removables);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 2", 2, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(remaining));
        assertTrue("must hold: removal result is true", result);
    }

    @Test
    public void test09RemoveManyElementsEvenIrrelevantAmongThem() {
        List<Box> boxes = Generator.makeBoxes(3);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 3", 3, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        List<Object> removables = new ArrayList<>() ;
        removables.add(0);
        removables.add(boxes.get(2));

        List<Box> remaining = boxes;

        Boolean result = null;
        Boolean illegalArgumentException = false;

        try {
            result = set.removeAll(removables);
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

    @Test
    public void test10RemoveManyElementsTwice() {
        List<Box> boxes = Generator.makeBoxes(5);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        List<Box> removables = Arrays.asList(boxes.get(0), boxes.get(2), boxes.get(4));
        List<Box> remaining = Arrays.asList(boxes.get(1), boxes.get(3));

        Boolean resultFirst = set.removeAll(removables);
        Boolean resultSecond = set.removeAll(removables);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 2", 2, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(remaining));
        assertTrue("must hold: removal first result is true", resultFirst);
        assertFalse("must hold: removal first result is true", resultSecond);
    }

    // --------------------------------

    @Test
    public void test11RemoveOneNullElement() {
        List<Box> boxes = Generator.makeBoxes(5);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        Boolean result = set.remove(null);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());
        assertTrue("must hold: rest boxes in Set", set.containsAll(boxes));
        assertFalse("must hold: removal result is false", result);
    }

    @Test
    public void test12RemoveAllManyElementsOneNullAmongThem() {
        List<Box> boxes = Generator.makeBoxes(5);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 5", 5, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(boxes));

        List<Box> removables = boxes.subList(2, 5);
        removables.add(null);
        removables.add(null);
        List<Box> remaining = boxes.subList(0, 2);

        Boolean result = set.removeAll(removables);

        assertFalse("must hold: Set is not empty", set.isEmpty());
        assertEquals("must hold: Set size is 2", 2, set.size());
        assertTrue("must hold: boxes in Set", set.containsAll(remaining));
        assertTrue("must hold: removal result is true", result);
    }

}
