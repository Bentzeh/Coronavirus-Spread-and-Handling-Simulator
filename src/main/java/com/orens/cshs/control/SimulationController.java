package com.orens.cshs.control;

import com.orens.cshs.display.Display;
import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.models.ParticipantsData;

import java.util.TimerTask;


/**
 * the controller, contains the "data container object"  and the "display object"
 * and the main loop that runs all of the logic
 *
 */
public class SimulationController extends TimerTask {

    private Display display;
    private ParticipantsData participantsData;


    public SimulationController() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SimulationController.Constructor()");
        participantsData = new ParticipantsData();
        display = new Display(participantsData);
    }


    @Override public void run() { mainLoop(); }


    private void mainLoop(){
        //LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SimulationController.mainLoop()");
        display.updateDisplayView();
        participantsData.doIteration(scheduledExecutionTime());
    }


}

