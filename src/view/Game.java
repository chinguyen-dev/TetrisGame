package view;

import javax.swing.JFrame;

import controller.BoardController;
import model.Board;
import model.patterns.builder.board.BoardBuilder;
import view.component.BoardComponent;

public class Game extends JFrame {
    private final BoardComponent boardComponent;
    private final BoardController boardController;
    private final Board modelMain;

    public Game(String title, int width, int height) {
        this.modelMain = new BoardBuilder()
                .width(10)
                .height(20)
                .delayTime(600)
                .build();
        this.boardComponent = new BoardComponent(modelMain);
        this.boardController = new BoardController(this.modelMain, boardComponent);
        this.registerObservable();
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

    public void registerObservable() {
        this.modelMain.addObserver(this.boardComponent);
    }
}
