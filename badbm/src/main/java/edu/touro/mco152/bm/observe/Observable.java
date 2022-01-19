package edu.touro.mco152.bm.observe;

public interface Observable {

    void registerObserver();

    void unregisterObserver();

    void notifyObservers();

}
