package com.orens.cshs.main;

import com.orens.cshs.control.SimulationController;
import com.orens.cshs.infra.utils.PropertiesFileReader;

import java.util.Timer;


public class CSHS{
    private final static Timer tickTimer = new Timer("tickTimer");

    public static void startSimulation() {
        long drawTimeInMilliseconds = PropertiesFileReader.getDrawTimeInMilliseconds();
        long tickTimeInMilliseconds = PropertiesFileReader.getTickTimeInMilliseconds();

        SimulationController simulationController = new SimulationController();
        simulationController.startPreparation();

        //timer.schedule(new SimulationController(), 0, tickTimeInMilliseconds);
        //timer.schedule(new SimulationController(), 0, drawTimeInMilliseconds);
        //tickTimer.scheduleAtFixedRate(simulationController, 0, tickTimeInMilliseconds);
        tickTimer.scheduleAtFixedRate(simulationController, 0, drawTimeInMilliseconds);
    }



}
