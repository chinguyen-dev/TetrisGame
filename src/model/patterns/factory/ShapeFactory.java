package model.patterns.factory;


import model.AShape;

import java.awt.*;

public class ShapeFactory {
    private static ShapeFactory instance;
    private final IShapeFactory iShape;
    private ShapeFactory() {
        this.iShape = ShapeFactoryImpl.getInstance();
    }
    public AShape createShape() {
        ShapeType shapeType = this.iShape.getRandomShape();
        Color color = this.iShape.getRandomColor();
        return this.iShape.generateShape(shapeType, color);
    }
    public static synchronized ShapeFactory getInstance() {
        if (instance == null) instance = new ShapeFactory();
        return instance;
    }
}
