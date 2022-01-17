package edu.touro.mco152.bm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;

public interface IuI {

    void handle(List<DiskMark> markList);

    void finished();

    boolean isAborted();

    void setPercentage(int percentComplete);

    void post(DiskMark diskMark);

    void addOnToPropertyChangeListener(PropertyChangeListener pce);

    void abort(boolean b);

    void start();
}
