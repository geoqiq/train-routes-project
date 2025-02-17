package org.example.trenuricaps.utils.observer;

public interface Observable{
    void addObserver(Observer e);
    // void rmvObserver(Observer e);
    void notifyObservers();
}
