package edu.touro.mco152.bm;

import edu.touro.mco152.bm.persist.DatabaseObserver;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.persist.EM;
import edu.touro.mco152.bm.ui.Gui;
import jakarta.persistence.EntityManager;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static edu.touro.mco152.bm.App.*;
import static edu.touro.mco152.bm.DiskMark.MarkType.READ;
import static edu.touro.mco152.bm.DiskMark.MarkType.WRITE;

/**
 * Run the disk benchmarking as a Swing-compliant thread (only one of these threads can run at
 * once.) Cooperates with Swing to provide and make use of interim and final progress and
 * information, which is also recorded as needed to the persistence store, and log.
 * <p>
 * Depends on static values that describe the benchmark to be done having been set in App and Gui classes.
 * The DiskRun class is used to keep track of and persist info about each benchmark at a higher level (a run),
 * while the DiskMark class described each iteration's result, which is displayed by the UI as the benchmark run
 * progresses.
 * <p>
 * This class only knows how to do 'read' or 'write' disk benchmarks. It is instantiated by the
 * startBenchmark() method.
 * <p>
 * To be Swing compliant this class extends SwingWorker and declares that its final return (when
 * doInBackground2() is finished) is of type Boolean, and declares that intermediate results are communicated to
 * Swing using an instance of the DiskMark class.
 */

public class DiskWorker {

    private final IuI ui;
    private final Executor executor = new Executor();

    public DiskWorker(IuI ui) {
        this.ui = ui;
    }

    public boolean start() throws Exception {

        if (readTest) {
            ReadCommand command = new ReadCommand(ui, numOfMarks, numOfBlocks, blockSizeKb, blockSequence);
            command.registerObserver(new DatabaseObserver());
            command.registerObserver(new Gui());

            executor.addCommand(command);
        }

        if (writeTest) {
            WriteCommand command = new WriteCommand(ui, numOfMarks, numOfBlocks, blockSizeKb, blockSequence);
            command.registerObserver(new DatabaseObserver());
            command.registerObserver(new Gui());

            executor.addCommand(command);
        }

        System.out.println("*** starting new worker thread");
        msg("Running readTest " + App.readTest + "   writeTest " + App.writeTest);
        msg("num files: " + App.numOfMarks + ", num blks: " + App.numOfBlocks
                + ", blk size (kb): " + App.blockSizeKb + ", blockSequence: " + App.blockSequence);

        executor.executeCommands();


        Gui.updateLegend();

        if (App.autoReset) {
            App.resetTestData();
            Gui.resetTestData();
        }


        if (App.readTest && App.writeTest && ui.isAborted()) {
            JOptionPane.showMessageDialog(Gui.mainFrame,
                    "For valid READ measurements please clear the disk cache by\n" +
                            "using the included RAMMap.exe or flushmem.exe utilities.\n" +
                            "Removable drives can be disconnected and reconnected.\n" +
                            "For system drives use the WRITE and READ operations \n" +
                            "independently by doing a cold reboot after the WRITE",
                    "Clear Disk Cache Now", JOptionPane.PLAIN_MESSAGE);
        }

        App.nextMarkNumber += App.numOfMarks;
        return true;
    }
}
