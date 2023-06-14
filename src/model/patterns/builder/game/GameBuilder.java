package model.patterns.builder.game;

import controller.IController;
import model.Board;
import view.Game;

import javax.swing.*;

public class GameBuilder implements IGameBuilder {
    protected String title;
    protected int width;
    protected int height;
    protected Board model;
    private IController controller;
    private JPanel component;

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
    public GameBuilder model(Board model) {
        this.model = model;
        return this;
    }

    @Override
    public GameBuilder controller(IController controller) {
        this.controller = controller;
        return this;
    }

    @Override
    public GameBuilder component(JPanel component) {
        this.component = component;
        return this;
    }

    @Override
    public Game build() {
        return new Game(this.title, this.width, this.height, this.model, this.controller, this.component);
    }
}
