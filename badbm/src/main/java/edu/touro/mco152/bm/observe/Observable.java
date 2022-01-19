package edu.touro.mco152.bm.observe;

public interface Observable {

    void registerObserver(Observer o);

    void unregisterObserver(Observer o);

    void notifyObservers();

}
