package edu.touro.mco152.bm;

import edu.touro.mco152.bm.ui.Gui;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import static edu.touro.mco152.bm.App.dataDir;

public class GuiUserInterface extends SwingWorker<Boolean, DiskMark> implements IuI {

    @Override
    public void handle(List<DiskMark> markList) {
        process(markList);
    }

    @Override
    public void finished() {
    }

    @Override
    public boolean isAborted() {
        return isCancelled();
    }

    @Override
    public void setPercentage(int i) {
        setProgress(i);
    }

    @Override
    public void post(DiskMark wMark) {
        publish(wMark);
    }

    @Override
    public void addOnToPropertyChangeListener(PropertyChangeListener pce) {
        addPropertyChangeListener(pce);
    }

    @Override
    public void abort(boolean b) {
        cancel(b);
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        return new DiskWorker(this).start();
    }

    @Override
    public void process(List<DiskMark> markList) {
        markList.stream().forEach((dm) -> {
            if (dm.type == DiskMark.MarkType.WRITE) {
                Gui.addWriteMark(dm);
            } else {
                Gui.addReadMark(dm);
            }
        });
    }

    @Override
    public void done() {
        if (App.autoRemoveData) {
            Util.deleteDirectory(dataDir);
        }
        App.state = App.State.IDLE_STATE;
        Gui.mainFrame.adjustSensitivity();
    }

    @Override
    public void start() {
        execute();
    }
}
