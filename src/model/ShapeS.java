package model;

import java.awt.Color;

public class ShapeS extends AShape {
    public ShapeS(Color color) {
        super.color = color;
        this.element = new int[][]{
                {0, 1, 1},
                {1, 1, 0},
        };
    }
}
