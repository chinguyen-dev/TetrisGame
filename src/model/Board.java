package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Random;

@SuppressWarnings("deprecation")
public class Board extends Observable {
	public static final String SHAPE = "TZOLISJ";
	public static final int BLOCK_SIZE = 30, BOARD_WIDTH = 10, BOARD_HEIGHT = 20;
	public static final int SPEED_NORMAL = 600, SPEED_FAST = 50;
	private int delayTime = SPEED_NORMAL;
	private long beginTime;
	private static Board instance;
	private IShapeFactory factory;
	private AShape currentShape;
	private int positionX = 0;
	private boolean collission = false;
	private Color[][] board;

	private Board() {
		this.board = new Color[BOARD_HEIGHT][BOARD_WIDTH];
		this.factory = ShapeFactoryImpl.getInstance();
		this.currentShape = this.getRandomShape();
	}

	public static Board getInstance() {
		if (instance == null) instance = new Board();
		return instance;
	}

	public AShape getRandomShape() {
		Random random = new Random();
		int index = random.nextInt(SHAPE.length());
		return this.factory.generateShape(Character.toString(SHAPE.charAt(index)));
	}

	public void notifyChanged() {
		this.setChanged();
		this.notifyObservers();
	}

	public void createNewShape() {
		if (this.collission) {
			int[][] element = this.currentShape.getElement();
			for (int row = 0; row < element.length; row++) {
				for (int col = 0; col < element[0].length; col++) {
					if (element[row][col] != 0) {
						this.board[this.currentShape.getY() + row][col + this.currentShape.getX()] = currentShape.getColor();
					}
				}
			}
			this.checkLine();
			this.currentShape = getRandomShape();
			this.collission = !this.collission;
		}
	}

	public void state() {
		this.createNewShape();
		this.currentShape.moveHorizontal(this.positionX);
		this.positionX = 0;
		if (System.currentTimeMillis() - this.beginTime > this.delayTime) {
			if (!this.currentShape.checkCollideBelow()) {
				this.collission = this.currentShape.checkVerticalForMovement(this.board, this.positionX);
				if (!collission) this.currentShape.moveDown();
			} else {
				this.collission = true;
			}
			this.beginTime = System.currentTimeMillis();
		}
	}

	private void checkLine() {
		int bottomLine = this.board.length - 1;
		for (int topLine = bottomLine; topLine > 0; topLine--) {
			int count = 0;
			for (int col = 0; col < this.board[0].length; col++) {
				if (this.board[topLine][col] != null) count++;
				this.board[bottomLine][col] = this.board[topLine][col];
			}
			if (count < this.board[0].length) bottomLine--;
		}
	}

	public Color[][] getBoard() {
		return this.board;
	}

	public AShape getCurrentShape() {
		return currentShape;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

}
