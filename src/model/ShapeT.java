package model;

import java.awt.Color;

public class ShapeT extends AShape {
	public ShapeT(Color color) {
		super.color = color;
		this.element = new int[][]{
				{ 1, 1, 1 },
				{ 0, 1, 0 },
				};
	}

}
