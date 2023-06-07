package model.patterns.builder.board;

import model.Board;

public interface IBoardBuilder {
    BoardBuilder width(int width);

    BoardBuilder height(int height);

    BoardBuilder delayTime(int delayTime);

    Board build();
}
