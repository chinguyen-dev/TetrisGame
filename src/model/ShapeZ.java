package model;

import java.awt.Color;

public class ShapeZ extends AShape {
    public ShapeZ(Color color) {
        super.color = color;
        this.element = new int[][]{
                {1, 1, 0},
                {0, 1, 1},
        };
    }
}
