package view;

import javax.swing.JFrame;

import controller.BoardController;
import model.Board;

public class Game extends JFrame {
	private final BoardComponent boardComponent;
	public Game(String title, int width, int height) {
		Board modelMain = Board.getInstance();
		this.boardComponent = new BoardComponent(modelMain);
		new BoardController(boardComponent);
		this.initialize(title, width, height);
	}

	public void initialize(String title, int width, int height) {
		this.setTitle(title);
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(this.boardComponent);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Game("Tetris game", 445, 629);
	}
}