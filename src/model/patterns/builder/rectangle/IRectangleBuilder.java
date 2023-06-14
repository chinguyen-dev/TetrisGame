package model.patterns.builder.rectangle;

import java.awt.*;

public interface IRectangleBuilder {
    RectangleBuilder positionX(int x);

    RectangleBuilder positionY(int y);

    RectangleBuilder width(int width);

    RectangleBuilder height(int height);

    Rectangle build();
}
