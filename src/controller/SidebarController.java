package controller;

import model.Board;
import model.StateGame;
import view.BoardComponent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SidebarController {
    private final Board board;
    private final BoardComponent boardComponent;

    public SidebarController(Board board, BoardComponent component) {
        this.board = board;
        this.boardComponent = component;
        this.action();
    }

    private void action() {
        this.boardComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (boardComponent.getStopBounds().contains(e.getX(), e.getY())) {
                    if (board.getStateGame() == StateGame.PLAY) {
                        board.setStateGame(StateGame.PAUSE);
                    } else if (board.getStateGame() == StateGame.PAUSE) {
                        board.setStateGame(StateGame.PLAY);
                    }
                }
                if (boardComponent.getRefreshBounds().contains(e.getX(), e.getY())) board.refresh();
            }
        });
    }
}
