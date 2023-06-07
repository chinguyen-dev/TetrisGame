package model.patterns.builder.game;

import view.Game;

public class GameBuilder implements IGameBuilder {
    private String title;
    private int width;
    private int height;

    @Override
    public GameBuilder title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public GameBuilder width(int width) {
        this.width = width;
        return this;
    }

    @Override
    public GameBuilder height(int height) {
        this.height = height;
        return this;
    }

    @Override
    public Game build() {
        return new Game(this.title, this.width, this.height);
    }
}
