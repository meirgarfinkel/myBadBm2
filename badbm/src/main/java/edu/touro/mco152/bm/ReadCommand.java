package edu.touro.mco152.bm;

import edu.touro.mco152.bm.persist.DiskRun;

public class ReadCommand implements ICommand{

    private final int numOfMarks;
    private final int numOfBlocks;
    private final int blockSizeKb;
    private final DiskRun.BlockSequence blockSequence;

    public ReadCommand(int numOfMarks, int numOfBlocks, int blockSizeKb, DiskRun.BlockSequence sequence){
        this.numOfMarks = numOfMarks;
        this.numOfBlocks = numOfBlocks;
        this.blockSizeKb = blockSizeKb;
        this.blockSequence = sequence;
    }

    @Override
    public void execute() {

    }
}
