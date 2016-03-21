package org.pek.unit.myset.util;

import org.pek.foreign.Box;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Generator {

    public static Function<Box, Integer> hashByName() {
        return
           b -> (b == null) ? 0 : Objects.hashCode(b.getName());
    }

    public static List<Box> makeBoxes (Integer amount) {

        List<Box> boxes = new ArrayList<>(amount);

        for (int i = 0; i < amount; i++) {
            String name = "name" + i;
            double w = 0.0 + i;
            Box box = new Box(name, w, Color.BLUE);
            boxes.add(box);
        }

        return boxes;
    }

}
