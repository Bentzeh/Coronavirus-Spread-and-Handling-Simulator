package com.orens.cshs.display;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.models.Board;


/**
 * abstract class that defines the the interface for output the simulation data to a output mechanism
 */
public abstract class AbstractOutput {

    protected Board board;

    /**
     *  constructor
     * @param board the board that the output mechanism reads and output
     */
    public AbstractOutput(Board board) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractOutput.Constructor()");
        this.board = board;
    }


    /**
     * initialize the chosen output mechanism internal objects
     */
    abstract public void InitializeDisplay();

    /**
     * update the chosen output mechanism internal objects with new data
     */
    abstract public void updateDisplayView();

}
