package model;

import java.awt.Color;

public class ShapeI extends AShape {
	public ShapeI(Color color) {
		super.color = color;
		this.element = new int[][]{
				{ 1, 1, 1 },
				};
	}
}
