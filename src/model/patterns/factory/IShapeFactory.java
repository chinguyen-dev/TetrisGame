package model.patterns.factory;


import model.AShape;

import java.awt.*;

public interface IShapeFactory {
    ShapeType getRandomShape();

    Color getRandomColor();

    AShape generateShape(ShapeType type, Color color);
}
