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
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MySetClearTest {

    @Test
    public void test01ClearWhenEmpty() {
        List<Box> boxes = Generator.makeBoxes(0);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        set.clear();

        assertTrue("must hold: Set is empty", set.isEmpty());
        assertEquals("must hold: Set size is 1", 0, set.size());
    }

    @Test
    public void test02ClearWhenFull() {
        List<Box> boxes = Generator.makeBoxes(1);
        MySet<Box> set = new MySet<>(Generator.hashByName(), HashMap<Integer, Box>::new, boxes);

        set.clear();

        assertTrue("must hold: Set is empty", set.isEmpty());
        assertEquals("must hold: Set size is 1", 0, set.size());
    }

}
