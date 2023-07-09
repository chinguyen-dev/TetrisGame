package controller;

import model.Board;
import model.StateGame;
import model.patterns.builder.game.GameBuilder;
import view.BoardComponent;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameController implements IController {
    private final Board model;
    private final BoardComponent component;
    private final Timer timer;

    public GameController(Board board) {
        this.model = board;
        this.component = new BoardComponent(board);
        this.actionMouse();
        this.actionKey();
        this.timer = new Timer(1000 / 60, e -> {
            this.model.state();
            this.component.repaint();
        });
        new GameBuilder()
                .title("Tetris Game")
                .width(445)
                .height(640)
                .model(board)
                .controller(this)
                .component(this.component)
                .build();
    }

    @Override
    public void start() {
        this.timer.start();
    }

    @Override
    public void stop() {
        this.timer.stop();
    }

    @Override
    public void refresh() {
        this.model.refresh();
    }

    @Override
    public void actionKey() {
        this.component.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                int positionX = 0;
                StateGame stateGame = model.getStateGame();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN -> model.setDelayTime(600);
                    case KeyEvent.VK_RIGHT -> positionX = 1;
                    case KeyEvent.VK_LEFT -> positionX = -1;
                    case KeyEvent.VK_SPACE -> {
                        if (model.getStateGame() == StateGame.PLAY) stateGame = StateGame.PAUSE;
                        if (model.getStateGame() == StateGame.PAUSE) stateGame = StateGame.PLAY;
                    }
                }
                model.setStateGame(stateGame);
                model.getCurrentShape().moveHorizontal(positionX, model.getWidth());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) model.setDelayTime(50);
            }
        });
    }

    @Override
    public void actionMouse() {
        this.component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (component.getStopBounds().contains(e.getX(), e.getY())) {
                    if (model.getStateGame() == StateGame.PLAY) {
                        model.setStateGame(StateGame.PAUSE);
                    } else if (model.getStateGame() == StateGame.PAUSE) {
                        model.setStateGame(StateGame.PLAY);
                    }
                }
                if (component.getRefreshBounds().contains(e.getX(), e.getY())) refresh();
            }
        });
    }
}
