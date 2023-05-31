package model;

import java.awt.Color;
import java.util.Random;

public class ShapeFactoryImpl implements IShapeFactory {
    private static ShapeFactoryImpl instance;
    private final Color[] colors = {
            Color.decode("#ed1c24"),
            Color.decode("#ff7f27"),
            Color.decode("#fff200"),
            Color.decode("#22b14c"),
            Color.decode("#00a2e8"),
            Color.decode("#a349a4"),
            Color.decode("#3f48cc")
    };

    private ShapeFactoryImpl() {
    }

    @Override
    public AShape generateShape(String type) {
        AShape shape = null;
        Color color = this.colors[new Random().nextInt(this.colors.length)];
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

    public static ShapeFactoryImpl getInstance() {
        if (instance == null) instance = new ShapeFactoryImpl();
        return instance;
    }

}
