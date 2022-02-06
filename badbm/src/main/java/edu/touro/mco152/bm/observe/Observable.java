package edu.touro.mco152.bm.observe;

/**
 * Interface for the object that is getting observed
 * registerObserver adds an observer
 * unregisterObserver removes an observer
 * notifyObserver is the call method to notify the observers
 */
public interface Observable {

    void registerObserver(Observer o);

    void unregisterObserver(Observer o);

    void notifyObservers();

}
