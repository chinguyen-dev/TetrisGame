package view;

import javax.swing.JFrame;

import controller.BoardController;
import model.Board;
import view.component.BoardComponent;

public class Game extends JFrame {
	private final BoardComponent boardComponent;
	private final BoardController boardController;
	public Game(String title, int width, int height) {
		Board modelMain = Board.getInstance();
		this.boardComponent = new BoardComponent(modelMain);
		this.boardController = new BoardController(boardComponent);
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
}
