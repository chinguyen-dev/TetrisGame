package model;

import java.awt.Color;

public class ShapeL extends AShape{
	public ShapeL(Color color) {
		super.color = color;
		this.element = new int[][]{
				{ 1, 1, 1 },
				{ 1, 0, 0 }
				};
	}
}
