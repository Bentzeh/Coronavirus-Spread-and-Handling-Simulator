package com.orens.cshs.display;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.models.ParticipantsData;


/**
 * this class mange the display
 */
public class Display {

    private AbstractOutput outputDisplay;

    /**
     * constructor
     * @param participantsData the "data" object that holds the data that needs to be displayed
     */
    public Display(ParticipantsData participantsData) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractOutput.Constructor()");

        outputDisplay = new GridLayoutOutput(participantsData.getBoard());
        //outputDisplay = new ConsoleOutput(participantsData.getBoard());
        outputDisplay.InitializeDisplay();
    }


    /**
     *  getter for the current output implementation
     * @return current output implementation
     */
    public AbstractOutput getOutputDisplay() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractOutput.getOutputDisplay()");
        return outputDisplay;
    }

    /**
     *  setter for the current output implementation
     * @param outputDisplay
     */
    public void setOutputDisplay(AbstractOutput outputDisplay) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractOutput.setOutputDisplay()");
        this.outputDisplay = outputDisplay;
    }


    /**
     * this method triggers the internal output implementation method that responsible for updating the object that needs to be displayed
     */
    public void updateDisplayView() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractOutput.updateDisplayView()");
        outputDisplay.updateDisplayView();
    }
}
