package org.pek.unit.mymap.util;

public class Thing {

    private static Integer COUNTER = 0;

    private String name;

    private Thing (String n) {
        name = n;
    }

    public static Thing makeThing (String nameSeed) {
        String extra = String.valueOf(COUNTER);
        COUNTER++;

        return new Thing(nameSeed + extra);
    }

    public String getName() {
        return name;
    }

    public static void reset() {
        COUNTER = 0;
    }
}
