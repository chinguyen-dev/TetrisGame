package controller;

import model.Board;
import view.BoardComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    private final Board board;
    private final BoardComponent boardComponent;
    private final SidebarController sidebarController;
    private final BoardController boardController;

    public GameController(Board board, BoardComponent boardComponent) {
        this.board = board;
        this.boardController = new BoardController(board, boardComponent);
        this.sidebarController = new SidebarController(board, boardComponent);
        this.boardComponent = boardComponent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.state();
        boardComponent.repaint();
    }
}
