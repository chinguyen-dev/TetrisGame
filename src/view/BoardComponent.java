package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.Board;

public class BoardComponent extends JPanel {
	private final Board board;
	public BoardComponent(Board board) {
		this.board = board;
		this.setFocusable(true);
		Timer timer = new Timer(1000 / 60, e -> {
			update();
			repaint();
		});
		timer.start();
	}

	private void update() {
		this.board.state();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());

		this.renderShape(g);
		
		this.rerender(g);

		this.renderBoard(g);
	}

	public void renderShape(Graphics g) {
		int[][] element = this.board.getCurrentShape().getElement();
		int x = this.board.getCurrentShape().getX();
		int y = this.board.getCurrentShape().getY();
		int blockSize = Board.BLOCK_SIZE;
		for (int raw = 0; raw < element.length; raw++) {
			for (int col = 0; col < element[0].length; col++) {
				if (element[raw][col] != 0) {
					g.setColor(this.board.getCurrentShape().getColor());
					g.fillRect(col * blockSize + x * blockSize, raw * blockSize + y * blockSize, blockSize, blockSize);
				}
			}
		}
	};

	public void renderBoard(Graphics g) {
		g.setColor(Color.white);
		for (int raw = 0; raw < Board.BOARD_HEIGHT; raw++) {
			g.drawLine(0, Board.BLOCK_SIZE * raw, Board.BLOCK_SIZE * Board.BOARD_WIDTH, Board.BLOCK_SIZE * raw);
		}
		for (int col = 0; col < Board.BOARD_WIDTH + 1; col++) {
			g.drawLine(Board.BLOCK_SIZE * col, 0, Board.BLOCK_SIZE * col, Board.BLOCK_SIZE * Board.BOARD_HEIGHT);
		}
	}

	public void rerender(Graphics g) {
		Color[][] board = this.board.getBoard();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] != null) {
					g.setColor(board[row][col]);
					g.fillRect(col * Board.BLOCK_SIZE, row * Board.BLOCK_SIZE, Board.BLOCK_SIZE, Board.BLOCK_SIZE);
				}
			}
		}
	}
}
