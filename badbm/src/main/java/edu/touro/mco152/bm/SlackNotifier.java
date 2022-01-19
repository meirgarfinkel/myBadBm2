package edu.touro.mco152.bm;

import edu.touro.mco152.bm.observe.Observer;
import edu.touro.mco152.bm.persist.DiskRun;

public class SlackNotifier implements Observer {

    SlackManager manager = new SlackManager("BadBm");

    @Override
    public void update() {

    }

    @Override
    public void update(Object o) {
        if (o instanceof DiskRun) {
            if (((DiskRun) o).getRunAvg() / ((DiskRun) o).getRunMax() > 3){
                manager.postMsg2OurChannel("Read benchmark was too slow");
            }
        }
    }
}
