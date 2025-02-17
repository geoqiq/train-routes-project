package org.example.trenuricaps.utils.observer;

import java.util.ArrayList;
import java.util.List;

public class ImplObservable implements Observable{

    List<Observer> lst = new ArrayList<>();

    @Override
    public void addObserver(Observer o) {
        lst.add(o);
    }

    // @Override
    // public void rmvObserver(Observer o) { lst.remove(o); }

    @Override
    public void notifyObservers() {
        for(Observer o:lst){
            o.update();
        }
    }
}