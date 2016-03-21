package org.pek.unit.mymap.util;

import org.pek.foreign.Box;

import java.awt.Color;
import java.util.*;
import java.util.function.Function;

public class Generator {

    public static Function<Box, Integer> hashByName() {
        return
            b -> (b == null) ? 0 : Objects.hashCode(b.getName());
    }

    public static List<AbstractMap.Entry<Box, Thing>> makePairs (Integer amount) {

        List<AbstractMap.Entry<Box, Thing>> pairs = new ArrayList<>(amount);

        Thing.reset();

        for (int i = 0; i < amount; i++) {
            String name = "name" + i;
            double w = 0.0 + i;
            Box box = new Box(name, w, Color.BLUE);

            Thing thing = Thing.makeThing(name + "_");
            AbstractMap.Entry<Box, Thing> pair = new AbstractMap.SimpleEntry<>(box, thing);

            pairs.add(pair);
        }

        return pairs;
    }

    public static List<AbstractMap.Entry<Box, Thing>> makePairsOfSameBox (Integer amount) {

        List<AbstractMap.Entry<Box, Thing>> pairs = new ArrayList<>(amount);
        Box box = new Box("name0", 0.0, Color.BLUE);

        Thing.reset();

        for (int i = 0; i < amount; i++) {
            Thing thing = Thing.makeThing(box.getName() + "_");
            AbstractMap.Entry<Box, Thing> pair = new AbstractMap.SimpleEntry<>(box, thing);

            pairs.add(pair);
        }

        return pairs;
    }

}
