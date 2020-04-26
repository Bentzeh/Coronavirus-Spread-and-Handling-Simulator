package com.orens.cshs.control;

import com.orens.cshs.display.AbstractDisplay;
import com.orens.cshs.display.GridLayoutDisplay;
import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.state.HealthyState;
import com.orens.cshs.logic.strategy.InspectorPersonStrategy;
import com.orens.cshs.logic.strategy.NormalPersonStrategy;
import com.orens.cshs.models.*;
import com.orens.cshs.models.pojos.Location;

import java.util.TimerTask;

public class SimulationController  extends TimerTask {
    private Board board;
    private AbstractDisplay display;
    private ParticipantsData participantsData;


    public void startPreparation(){
        board = new Board();

        //display = new ConsoleDisplay(board);
        display = new GridLayoutDisplay(board);
        display.InitializeDisplay();

        participantsData = new ParticipantsData();

        initParticipants();
    }

    private void initParticipants() {

        int amountOfHealthyPeople = PropertiesFileReader.getInitialAmountOfHealthyPeople();
        for (int i = 0; i < amountOfHealthyPeople; ++i) {

            Location location = new Location().getRandomLocation();
            AbstractHealthState healthyState = new HealthyState();
            Participant participant = new NormalPerson(location, healthyState, new NormalPersonStrategy(board, participantsData));

            participant.getLogicStrategy().initializeBoardWithParticipant(participant);
            participantsData.addSimulationParticipant(participant);
        }

//        int amountOfCarryingPeople = PropertiesFileReader.getInitialAmountOfCarryingPeople();
//        for (int i = 0; i < amountOfCarryingPeople; ++i) {
//            Location location = new Location().getRandomLocation();
//            AbstractHealthState healthyState = new CarryingState();
//            Participant participant = new NormalPerson(location, healthyState, new NormalPersonStrategy(board, participantsData));
//
//            strategy.initializeBoardWithParticipant(participant);
//            participantsData.addSimulationParticipant(participant);
//        }
//
//        int amountOfSickPeople = PropertiesFileReader.getInitialAmountOfSickPeople();
//        for (int i = 0; i < amountOfSickPeople; ++i) {
//            Location location = new Location().getRandomLocation();
//            AbstractHealthState healthyState = new SickState();
//            Participant participant = new NormalPerson(location, healthyState, new NormalPersonStrategy(board, participantsData));
//
//            strategy.initializeBoardWithParticipant(participant);
//            participantsData.addSimulationParticipant(participant);
//        }
//
        int amountOfInspectors = PropertiesFileReader.getInitialAmountOfInspectors();
        for (int i = 0; i < amountOfInspectors; ++i) {
            Location location = new Location().getRandomLocation();
            AbstractHealthState healthyState = new HealthyState();
            Participant participant = new InspectorPerson(location, healthyState, new InspectorPersonStrategy(board, participantsData));

            participant.getLogicStrategy().initializeBoardWithParticipant(participant);
            participantsData.addSimulationParticipant(participant);
        }
    }


    @Override public void run() { mainLoop(); }



    private void mainLoop(){
        display.updateDisplayView();
        participantsData.doIteration(scheduledExecutionTime());
    }


}

