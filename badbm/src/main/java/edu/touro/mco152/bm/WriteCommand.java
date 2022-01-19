package edu.touro.mco152.bm;

import edu.touro.mco152.bm.persist.DiskRun.BlockSequence;

public class WriteCommand implements ICommand{

    private final int numOfMarks;
    private final int numOfBlocks;
    private final int blockSizeKb;
    private final BlockSequence blockSequence;

    public WriteCommand(int numOfMarks, int numOfBlocks, int blockSizeKb, BlockSequence sequence){
        this.numOfMarks = numOfMarks;
        this.numOfBlocks = numOfBlocks;
        this.blockSizeKb = blockSizeKb;
        this.blockSequence = sequence;
    }

    @Override
    public void execute() {

    }

}
