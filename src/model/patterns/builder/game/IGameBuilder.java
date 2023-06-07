package model.patterns.builder.game;

import view.Game;

public interface IGameBuilder {
    GameBuilder title(String title);
    GameBuilder width(int width);
    GameBuilder height(int height);
    Game build();
}
