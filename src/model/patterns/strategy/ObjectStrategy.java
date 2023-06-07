package model.patterns.strategy;

import model.patterns.observer.Observable;

public interface ObjectStrategy {
    void updateAttribute(Observable observable);
}
