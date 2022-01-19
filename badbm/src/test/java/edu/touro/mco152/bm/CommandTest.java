package edu.touro.mco152.bm;

import edu.touro.mco152.bm.persist.DiskRun.BlockSequence;
import edu.touro.mco152.bm.ui.Gui;
import edu.touro.mco152.bm.ui.MainFrame;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CommandTest implements IuI{

    private int percentComplete = 0;
    private ArrayList<DiskMark> dmList = new ArrayList<>();
    private final Executor executor = new Executor();

    @BeforeAll
    private static void init(){
        /// Do the minimum of what  App.init() would do to allow to run.
        Gui.mainFrame = new MainFrame();
        App.p = new Properties();
        App.loadConfig();
        System.out.println(App.getConfigString());
        Gui.progressBar = Gui.mainFrame.getProgressBar(); //must be set or get Nullptr

        // configure the embedded DB in .jDiskMark
        System.setProperty("derby.system.home", App.APP_CACHE_DIR);

        // code from startBenchmark
        //4. create data dir reference
        App.dataDir = new File(App.locationDir.getAbsolutePath()+File.separator+App.DATADIRNAME);

        //5. remove existing test data if exist
        if (App.dataDir.exists()) {
            if (App.dataDir.delete()) {
                App.msg("removed existing data dir");
            } else {
                App.msg("unable to remove existing data dir");
            }
        }
        else
        {
            App.dataDir.mkdirs(); // create data dir if not already present
        }
    }

    @BeforeEach
    private void runBenchMark() throws Exception {
        executor.addCommand(new ReadCommand(this,25, 128, 2048, BlockSequence.SEQUENTIAL));
        executor.addCommand(new WriteCommand(this,25, 128, 2048, BlockSequence.SEQUENTIAL));
        executor.executeCommands();
    }

    @Test
    public void finishedCompletelyTest(){
        Assert.isTrue(percentComplete == 50, "isn't 50");
    }

    @Test
    public void validDataTest(){
        for (DiskMark data: dmList) {
            Assert.isTrue(data.getCumAvg() > 0.1, "DiskMark getCumAvg is 0");
        }
    }

    @Override
    public void handle(List<DiskMark> markList) {

    }

    @Override
    public void finished() {

    }

    @Override
    public boolean isAborted() {
        return false;
    }

    @Override
    public void setPercentage(int percentComplete) {
        this.percentComplete = percentComplete;
    }

    @Override
    public void post(DiskMark diskMark) {
        dmList.add(diskMark);
    }

    @Override
    public void addOnToPropertyChangeListener(PropertyChangeListener pce) {

    }

    @Override
    public void abort(boolean b) {

    }

    @Override
    public void start() {

    }
}
