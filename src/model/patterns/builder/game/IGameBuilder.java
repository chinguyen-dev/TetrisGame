package model.patterns.builder.game;

import controller.IController;
import model.Board;
import view.Game;

import javax.swing.*;

public interface IGameBuilder {
    GameBuilder title(String title);

    GameBuilder width(int width);

    GameBuilder height(int height);

    GameBuilder model(Board model);

    GameBuilder controller(IController controller);

    GameBuilder component(JPanel component);

    Game build();
}
