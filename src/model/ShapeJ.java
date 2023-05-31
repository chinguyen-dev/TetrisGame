package model;

import java.awt.Color;

public class ShapeJ extends AShape {
	public ShapeJ(Color color) {
		super.color = color;
		this.element = new int[][]{
				{ 1, 1, 1 },
				{ 0, 0, 1 },
				};
	}
}
