package model.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private final List<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer o : this.observers) o.update();
    }
}
