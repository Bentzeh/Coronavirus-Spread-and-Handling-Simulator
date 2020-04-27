package com.orens.cshs.control;

import com.orens.cshs.display.Display;
import com.orens.cshs.models.ParticipantsData;

import java.util.TimerTask;

public class SimulationController extends TimerTask {

    private Display display;
    private ParticipantsData participantsData;


    public void startPreparation(){
        participantsData = new ParticipantsData();
        display = new Display(participantsData);
    }




    @Override public void run() { mainLoop(); }



    private void mainLoop(){
        display.updateDisplayView();
        participantsData.doIteration(scheduledExecutionTime());
    }


}

