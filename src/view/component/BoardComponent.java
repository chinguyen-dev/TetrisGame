package view.component;

import model.AShape;
import model.Board;
import model.StateGame;
import model.patterns.observer.*;
import model.patterns.strategy.ObjectStrategy;

import javax.swing.*;
import java.awt.*;

public class BoardComponent extends JPanel implements Observer, ObjectStrategy {
    private AShape currentShape;
    private Color[][] shapesFreeze;
    private int boardHeight;
    private int boardWidth;
    private final Board board;
    private StateGame stateGame;

    public BoardComponent(Board board) {
        this.board = board;
        this.updateAttribute(board);
        this.setFocusable(true);
        this.start();
    }

    public void start() {
        Timer timer = new Timer(1000 / 60, e -> {
            board.state();
            repaint();
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        this.drawShape(g);

        this.drawShapeFreeze(g);

        this.drawBoard(g);

        if (stateGame == StateGame.OVER) {
            g.setColor(Color.white);
            g.drawString("Game Over", 130, 200);
        }
    }

    public void drawShape(Graphics g) {
        int[][] element = this.currentShape.getElement();
        int x = this.currentShape.getX();
        int y = this.currentShape.getY();
        int blockSize = this.currentShape.getSize();
        for (int raw = 0; raw < element.length; raw++) {
            for (int col = 0; col < element[0].length; col++) {
                if (element[raw][col] != 0) {
                    g.setColor(this.currentShape.getColor());
                    g.fillRect(col * blockSize + x * blockSize, raw * blockSize + y * blockSize, blockSize, blockSize);
                }
            }
        }
    }

    public void drawBoard(Graphics g) {
        g.setColor(Color.white);
        int blockSize = this.currentShape.getSize();
        for (int raw = 0; raw < this.boardHeight; raw++) {
            g.drawLine(0, blockSize * raw, blockSize * this.boardWidth, blockSize * raw);
        }
        for (int col = 0; col < this.boardWidth + 1; col++) {
            g.drawLine(blockSize * col, 0, blockSize * col, blockSize * boardHeight);
        }
    }

    public void drawShapeFreeze(Graphics g) {
        int blockSize = this.currentShape.getSize();
        for (int row = 0; row < this.shapesFreeze.length; row++) {
            for (int col = 0; col < this.shapesFreeze[row].length; col++) {
                if (this.shapesFreeze[row][col] != null) {
                    g.setColor(this.shapesFreeze[row][col]);
                    g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
                }
            }
        }
    }

    @Override
    public void update(Observable observable) {
        this.updateAttribute(observable);
    }

    @Override
    public void updateAttribute(Observable observable) {
        if (observable instanceof Board b) {
            this.shapesFreeze = b.getShapesFreeze();
            this.currentShape = b.getCurrentShape();
            this.boardWidth = b.getWidth();
            this.boardHeight = b.getHeight();
            this.stateGame = b.getStateGame();
        }
    }
}
