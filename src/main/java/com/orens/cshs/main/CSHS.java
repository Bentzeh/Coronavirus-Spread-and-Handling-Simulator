package com.orens.cshs.main;

import com.orens.cshs.control.SimulationController;
import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.PropertiesFileReader;

import java.util.Timer;

/**
 *  the "Main" class here we initialize the tick Thread that run the main loop periodically
 */
public class CSHS{
    private final static Timer tickTimer = new Timer("tickTimer");

    public static void startSimulation() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered CSHS.startSimulation()");
        long tickTimeInMilliseconds = PropertiesFileReader.getTickTimeInMilliseconds();
        tickTimer.scheduleAtFixedRate(new SimulationController(), 0, tickTimeInMilliseconds);
    }



}
