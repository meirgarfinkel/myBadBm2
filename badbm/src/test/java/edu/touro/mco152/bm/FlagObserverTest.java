package edu.touro.mco152.bm;

import edu.touro.mco152.bm.observe.Observer;

public class FlagObserverTest implements Observer {

    public boolean gotCalled = false;

    @Override
    public void update() {

    }

    @Override
    public void update(Object o) {
        gotCalled = true;
    }
}
