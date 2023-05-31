package model;

import java.awt.Color;

public class ShapeO extends AShape {
	public ShapeO(Color color) {
		super.color = color;
		this.element = new int[][]{
				{ 1, 1 },
				{ 1, 1 }
				};
	}
}
