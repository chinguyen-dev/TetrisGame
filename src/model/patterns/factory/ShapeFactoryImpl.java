package model.patterns.factory;

import model.*;

import java.awt.Color;
import java.util.Random;

public class ShapeFactoryImpl implements IShapeFactory {
    private static ShapeFactoryImpl instance;
    private final Random random;
    private final Color[] colors = {
            Color.decode("#EA2027"),
            Color.decode("#27ae60"),
            Color.decode("#3498db"),
            Color.decode("#8e44ad"),
            Color.decode("#f1c40f"),
            Color.decode("#d35400"),
            Color.decode("#1abc9c"),
            Color.decode("#e056fd"),
    };

    private ShapeFactoryImpl() {
        this.random = new Random();
    }

    @Override
    public ShapeType getRandomShape() {
        ShapeType[] shapeTypes = ShapeType.values();
        return shapeTypes[this.random.nextInt(shapeTypes.length)];
    }

    @Override
    public Color getRandomColor() {
        return this.colors[this.random.nextInt(this.colors.length)];
    }

    @Override
    public AShape generateShape(ShapeType type, Color color) {
        AShape shape;
        switch (type) {
            case SHAPE_T -> shape = new ShapeT(color);
            case SHAPE_Z -> shape = new ShapeZ(color);
            case SHAPE_O -> shape = new ShapeO(color);
            case SHAPE_L -> shape = new ShapeL(color);
            case SHAPE_I -> shape = new ShapeI(color);
            case SHAPE_S -> shape = new ShapeS(color);
            case SHAPE_J -> shape = new ShapeJ(color);
            default -> throw new RuntimeException("Generate shape failed with type: " + type);
        }
        return shape;
    }

    public static ShapeFactoryImpl getInstance() {
        if (instance == null) instance = new ShapeFactoryImpl();
        return instance;
    }
}
