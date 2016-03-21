package org.pek.foreign;

import java.awt.Color;

public class Factory {

    public static Box produce (String name, double weight, Color color) {
        return new Box (name, weight, color);
    }

}
