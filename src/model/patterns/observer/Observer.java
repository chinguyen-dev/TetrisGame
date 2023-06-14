package model.patterns.observer;

public interface Observer {
    void update(Observable observable);
    void setAttributes(Observable observable);
}
