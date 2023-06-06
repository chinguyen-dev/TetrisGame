package model;

import java.awt.Color;

public abstract class AShape {

    protected int[][] element;
    protected Color color;
    protected int x, y;
    protected int size;

    public AShape() {
        this.x = 4;
        this.y = 0;
        this.size = 30;
    }

    public void moveDown() {
        this.y++;
    }

    public void moveHorizontal(int position) {
        if (!(this.x + this.element[0].length + position > Board.BOARD_WIDTH) && !(this.x + position < 0))
            this.x += position;
    }

    public boolean checkCollideBelow() {
        return this.y + 1 + this.element.length > Board.BOARD_HEIGHT;
    }

    public boolean checkVerticalForMovement(Color[][] board, int positionX) {
        for (int raw = 0; raw < this.element.length; raw++) {
            for (int col = 0; col < this.element[raw].length; col++) {
                if (this.element[raw][col] != 0)
                    if (board[this.y + 1 + raw][this.x + positionX + col] != null) {
                        return true;
                    }
            }
        }
        return false;
    }

    public int[][] getElement() {
        return element;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getSize() {
        return size;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}
