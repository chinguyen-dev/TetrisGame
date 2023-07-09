package view;

import model.AShape;
import model.Board;
import model.ImageLoader;
import model.StateGame;
import model.patterns.builder.rectangle.RectangleBuilder;
import model.patterns.observer.Observable;
import model.patterns.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BoardComponent extends JPanel implements Observer {
    private BufferedImage pause, refresh;
    private Rectangle stopBounds, refreshBounds;
    private AShape currentShape;
    private Color[][] shapesFreeze;
    private int boardHeight, boardWidth, score;
    private StateGame stateGame;

    public BoardComponent(Observable observable) {
        this.setAttributes(observable);
        this.initialImage();
        this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        this.drawShape(g);

        this.drawShapeFreeze(g);

        this.drawBoard(g);

        this.drawTitleState(g);

        this.drawScore(g);

        this.drawButton(g);
    }

    private void drawButton(Graphics g) {
        g.drawImage(pause, stopBounds.x, stopBounds.y, null);
        g.drawImage(refresh, refreshBounds.x, refreshBounds.y, null);
    }

    private void drawShape(Graphics g) {
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

    private void drawBoard(Graphics g) {
        g.setColor(Color.white);
        int blockSize = this.currentShape.getSize();
        for (int raw = 0; raw < this.boardHeight + 1; raw++) {
            g.drawLine(0, blockSize * raw, blockSize * this.boardWidth, blockSize * raw);
        }
        for (int col = 0; col < this.boardWidth + 1; col++) {
            g.drawLine(blockSize * col, 0, blockSize * col, blockSize * boardHeight);
        }
    }

    private void drawShapeFreeze(Graphics g) {
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

    private void drawTitleState(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Georgia", Font.BOLD, 30));
        if (stateGame == StateGame.OVER) g.drawString("Game Over", 63, 629 / 2);
        if (stateGame == StateGame.PAUSE) g.drawString("Game Pause", 55, 629 / 2);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Georgia", Font.BOLD, 20));
        g.drawString("SCORE", 445 - 125, 629 / 2);
        g.drawString(this.score + "", 445 - 125, 629 / 2 + 30);
    }

    private void initialImage() {
        this.pause = ImageLoader.loadImage("/Pause.png");
        this.refresh = ImageLoader.loadImage("/refresh.png");
        this.stopBounds = new RectangleBuilder()
                .positionX(350)
                .positionY(500)
                .width(this.pause.getWidth())
                .height(this.pause.getHeight() + this.pause.getHeight() / 2)
                .build();
        this.refreshBounds = new RectangleBuilder()
                .positionX(350)
                .positionY(500 - this.refresh.getHeight() - 20)
                .width(this.refresh.getWidth())
                .height(this.refresh.getHeight() + this.refresh.getHeight() / 2)
                .build();
    }

    @Override
    public void update(Observable observable) {
        this.setAttributes(observable);
    }

    @Override
    public void setAttributes(Observable observable) {
        if (observable instanceof Board b) {
            this.shapesFreeze = b.getShapesFreeze();
            this.currentShape = b.getCurrentShape();
            this.boardWidth = b.getWidth();
            this.boardHeight = b.getHeight();
            this.stateGame = b.getStateGame();
            this.score = b.getScore();
        }
    }

    public Rectangle getStopBounds() {
        return stopBounds;
    }

    public Rectangle getRefreshBounds() {
        return refreshBounds;
    }
}
