package model;

import model.patterns.factory.IShapeFactory;
import model.patterns.factory.ShapeFactoryImpl;

import java.awt.*;

public class Board {
    public static final int BOARD_WIDTH = 10, BOARD_HEIGHT = 20;
    private static Board instance;
    private final IShapeFactory factory;
    private final Color[][] shapesFreeze;
    private long beginTime;
    private int delayTime = 600;
    private boolean collision = false;
    private AShape currentShape;

    private Board() {
        this.shapesFreeze = new Color[BOARD_HEIGHT][BOARD_WIDTH];
        this.factory = ShapeFactoryImpl.getInstance();
        this.currentShape = this.getNewShape();
    }

    public static synchronized Board getInstance() {
        if (instance == null) instance = new Board();
        return instance;
    }

    public void createNewShape() {
        if (this.collision) {
            int[][] element = this.currentShape.getElement();
            for (int row = 0; row < element.length; row++) {
                for (int col = 0; col < element[0].length; col++) {
                    if (element[row][col] != 0) {
                        this.shapesFreeze[this.currentShape.getY() + row][col + this.currentShape.getX()] = currentShape.getColor();
                    }
                }
            }
            this.checkLine();
            this.currentShape = this.getNewShape();
            this.collision = !this.collision;
        }
    }

    public AShape getNewShape() {
        return this.factory.getRandomShape();
    }

    public void state() {
        this.createNewShape();
        if (System.currentTimeMillis() - this.beginTime > this.delayTime) {
            if (!this.currentShape.checkCollideBelow()) {
                this.collision = this.currentShape.checkVerticalForMovement(this.shapesFreeze);
                if (!collision) this.currentShape.moveDown();
            } else {
                this.collision = true;
            }
            this.beginTime = System.currentTimeMillis();
        }
    }

    private void checkLine() {
        int bottomLine = this.shapesFreeze.length - 1;
        for (int topLine = bottomLine; topLine > 0; topLine--) {
            int count = 0;
            for (int col = 0; col < this.shapesFreeze[0].length; col++) {
                if (this.shapesFreeze[topLine][col] != null) count++;
                this.shapesFreeze[bottomLine][col] = this.shapesFreeze[topLine][col];
            }
            if (count < this.shapesFreeze[0].length) bottomLine--;
        }
    }

    public Color[][] getBoard() {
        return this.shapesFreeze;
    }

    public AShape getCurrentShape() {
        return currentShape;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }
}
