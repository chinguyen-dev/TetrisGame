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
    public AShape getRandomShape() {
        String shape = "TZOLISJ";
        int shapeIndex = this.random.nextInt(shape.length());
        int colorIndex = this.random.nextInt(this.colors.length);
        String shapeType = Character.toString(shape.charAt(shapeIndex));
        Color color = this.colors[colorIndex];
        return this.generateShape(shapeType, color);
    }

    public AShape generateShape(String type, Color color) {
        AShape shape = null;
        switch (type.toUpperCase()) {
            case "T" -> shape = new ShapeT(color);
            case "Z" -> shape = new ShapeZ(color);
            case "O" -> shape = new ShapeO(color);
            case "L" -> shape = new ShapeL(color);
            case "I" -> shape = new ShapeI(color);
            case "S" -> shape = new ShapeS(color);
            case "J" -> shape = new ShapeJ(color);
            default -> System.out.println("Generate failed with type: " + type);
        }
        return shape;
    }

    public static synchronized ShapeFactoryImpl getInstance() {
        if (instance == null) instance = new ShapeFactoryImpl();
        return instance;
    }
}
