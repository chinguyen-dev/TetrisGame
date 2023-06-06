package view.component;

import model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardComponent extends JPanel {
    private final Board board;

    public BoardComponent(Board board) {
        this.board = board;
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
    }

    public void drawShape(Graphics g) {
        int[][] element = this.board.getCurrentShape().getElement();
        int x = this.board.getCurrentShape().getX();
        int y = this.board.getCurrentShape().getY();
        int blockSize = this.board.getCurrentShape().getSize();
        for (int raw = 0; raw < element.length; raw++) {
            for (int col = 0; col < element[0].length; col++) {
                if (element[raw][col] != 0) {
                    g.setColor(this.board.getCurrentShape().getColor());
                    g.fillRect(col * blockSize + x * blockSize, raw * blockSize + y * blockSize, blockSize, blockSize);
                }
            }
        }
    }

    public void drawBoard(Graphics g) {
        g.setColor(Color.white);
        int blockSize = this.board.getCurrentShape().getSize();
        for (int raw = 0; raw < Board.BOARD_HEIGHT; raw++) {
            g.drawLine(0, blockSize * raw, blockSize * Board.BOARD_WIDTH, blockSize * raw);
        }
        for (int col = 0; col < Board.BOARD_WIDTH + 1; col++) {
            g.drawLine(blockSize * col, 0, blockSize * col, blockSize * Board.BOARD_HEIGHT);
        }
    }

    public void drawShapeFreeze(Graphics g) {
        Color[][] board = this.board.getBoard();
        int blockSize = this.board.getCurrentShape().getSize();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != null) {
                    g.setColor(board[row][col]);
                    g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
                }
            }
        }
    }
}
