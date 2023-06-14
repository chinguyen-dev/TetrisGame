package model.patterns.observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Observable {
    private final List<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer... observer) {
        this.observers.addAll(Arrays.asList(observer));
    }

    public void removeObserver(Observer... observer) {
        this.observers.removeAll(Arrays.asList(observer));
    }

    public void notifyObservers() {
        this.observers.forEach(observer -> observer.update(this));
    }
}
