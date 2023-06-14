package model.patterns.builder.rectangle;

import java.awt.*;

public class RectangleBuilder implements IRectangleBuilder {
    private int x, y;
    private int width, height;

    @Override
    public RectangleBuilder positionX(int x) {
        this.x = x;
        return this;
    }

    @Override
    public RectangleBuilder positionY(int y) {
        this.y = y;
        return this;
    }

    @Override
    public RectangleBuilder width(int width) {
        this.width = width;
        return this;
    }

    @Override
    public RectangleBuilder height(int height) {
        this.height = height;
        return this;
    }

    @Override
    public Rectangle build() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
}
