package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Board;
import view.component.BoardComponent;

public class BoardController {
    private final Board board;
    private final BoardComponent boardComponent;

    public BoardController(Board board, BoardComponent component) {
        this.board = board;
        this.boardComponent = component;
        this.action();
    }

    public void action() {
        this.boardComponent.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                int positionX = 0;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN -> board.setDelayTime(600);
                    case KeyEvent.VK_RIGHT -> positionX = 1;
                    case KeyEvent.VK_LEFT -> positionX = -1;
                }
                board.getCurrentShape().moveHorizontal(positionX, board.getWidth());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) board.setDelayTime(50);
            }
        });
    }

}
