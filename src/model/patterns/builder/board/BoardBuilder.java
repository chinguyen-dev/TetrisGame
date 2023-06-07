package model.patterns.builder.board;

import model.Board;

public class BoardBuilder implements IBoardBuilder {
    private int width;
    private int height;
    private int delayTime;

    @Override
    public BoardBuilder width(int width) {
        this.width = width;
        return this;
    }

    @Override
    public BoardBuilder height(int height) {
        this.height = height;
        return this;
    }

    @Override
    public BoardBuilder delayTime(int delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    @Override
    public Board build() {
        return new Board(this.width, this.height, this.delayTime);
    }
}
