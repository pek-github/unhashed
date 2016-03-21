package org.pek.foreign;

import java.awt.Color;

public final class Box {
    private final String name;
    private final double weight;
    private final Color color;

    public Box (String n, double w, Color c) {
        name = n;
        weight = w;
        color = c;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }
}
