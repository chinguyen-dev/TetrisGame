package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Board;
import view.component.BoardComponent;

public class BoardController {
    private final Board model;
    private final BoardComponent boardComponent;
    public BoardController(BoardComponent component) {
        this.model = Board.getInstance();
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
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_DOWN) {
                    model.setDelayTime(600);
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    model.setPositionX(1);
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    model.setPositionX(-1);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) model.setDelayTime(50);
            }
        });
    }

}
