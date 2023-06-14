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
    private final Board board;
    private final BoardComponent component;
    private final Timer timer;

    public GameController(Board board) {
        this.board = board;
        this.component = new BoardComponent(board);
        this.actionMouse();
        this.actionKey();
        JFrame view = new GameBuilder()
                .title("Tetris Game")
                .width(445)
                .height(629)
                .model(board)
                .controller(this)
                .component(this.component)
                .build();
        this.timer = new Timer(1000 / 60, e -> {
            this.board.state();
            this.component.repaint();
        });
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
        this.board.refresh();
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
                StateGame stateGame = board.getStateGame();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN -> board.setDelayTime(600);
                    case KeyEvent.VK_RIGHT -> positionX = 1;
                    case KeyEvent.VK_LEFT -> positionX = -1;
                    case KeyEvent.VK_SPACE -> {
                        if (board.getStateGame() == StateGame.PLAY) stateGame = StateGame.PAUSE;
                        if (board.getStateGame() == StateGame.PAUSE) stateGame = StateGame.PLAY;
                    }
                }
                board.setStateGame(stateGame);
                board.getCurrentShape().moveHorizontal(positionX, board.getWidth());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) board.setDelayTime(50);
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
                    if (board.getStateGame() == StateGame.PLAY) {
                        board.setStateGame(StateGame.PAUSE);
                    } else if (board.getStateGame() == StateGame.PAUSE) {
                        board.setStateGame(StateGame.PLAY);
                    }
                }
                if (component.getRefreshBounds().contains(e.getX(), e.getY())) refresh();
            }
        });
    }
}
