package view;

import controller.IController;
import model.Board;
import model.patterns.observer.Observer;

import javax.swing.*;

public class Game extends JFrame {
    private final JPanel component;
    private final Board model;

    public Game(String title, int width, int height, Board model, IController controller, JPanel component) {
        this.model = model;
        this.component = component;
        this.registerObservable();
        this.initialize(title, width, height);
        controller.start();
    }

    public void initialize(String title, int width, int height) {
        this.setTitle(title);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(this.component);
        this.setVisible(true);
    }

    public void registerObservable() {
        this.model.addObserver((Observer) this.component);
    }
}
