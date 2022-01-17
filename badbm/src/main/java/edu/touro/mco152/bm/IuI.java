package edu.touro.mco152.bm;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.List;

public interface IuI {
    boolean start() throws Exception;

    void handle(List<DiskMark> markList);

    void finished();

    boolean aborted();

    void setPercentage(int percentComplete);

    void post(DiskMark diskMark);
}
