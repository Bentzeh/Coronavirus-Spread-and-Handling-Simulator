package com.orens.cshs.models;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.strategy.StandardStrategy;

import java.util.Iterator;


/**
 * this class contains the data object of the simulation such as board, and list of participants
 */
public class ParticipantsData {
    private Board board;
    private final SimulationParticipantsList<IInvocable> simulationParticipants;


    /**
     * constructor
     */
    public ParticipantsData() {
        this.board = new Board();
        this.simulationParticipants = new SimulationParticipantsList<>();
        initParticipants();
    }

    /**
     * initialize the list of participants with the given data from the properties file
     */
    private void initParticipants() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered ParticipantsData.initParticipants()");

        int amountOfHealthyPeople = PropertiesFileReader.getInitialAmountOfHealthyPeople();
        for (int i = 0; i < amountOfHealthyPeople; ++i) {

            Participant participant = new NormalPerson(new StandardStrategy(this));
            participant.setCurrentHealthState(AbstractHealthState.State.Healthy);

            this.addSimulationParticipant(participant);
        }

        int amountOfCarryingPeople = PropertiesFileReader.getInitialAmountOfCarryingPeople();
        for (int i = 0; i < amountOfCarryingPeople; ++i) {

            Participant participant = new NormalPerson(new StandardStrategy(this));
            participant.setCurrentHealthState(AbstractHealthState.State.Carrying);

            this.addSimulationParticipant(participant);
        }

        int amountOfSickPeople = PropertiesFileReader.getInitialAmountOfSickPeople();
        for (int i = 0; i < amountOfSickPeople; ++i) {

            Participant participant = new NormalPerson(new StandardStrategy(this));
            participant.setCurrentHealthState(AbstractHealthState.State.Sick);

            this.addSimulationParticipant(participant);
        }

        int amountOfInspectors = PropertiesFileReader.getInitialAmountOfInspectors();
        for (int i = 0; i < amountOfInspectors; ++i) {

            Participant participant = new InspectorPerson(new StandardStrategy(this));
            participant.setCurrentHealthState(AbstractHealthState.State.Healthy);

            this.addSimulationParticipant(participant);
        }
    }

    /**
     * retrieves the participants list iterator
     * @return participants list iterator
     */
    public Iterator<IInvocable> getSimulationParticipantsIterator() {
        return simulationParticipants.iterator();
    }

    /**
     * add a participant to the participants list
     * @param participant a participant that we want to add to the participant list
     * @return true if succeeded, false otherwise
     */
    public boolean addSimulationParticipant(Participant participant){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered ParticipantsData.addSimulationParticipant()");

        boolean isAddedToData = simulationParticipants.add(participant);
        boolean isAddedToBoard = board.addParticipantToLocation(participant);
        return isAddedToData && isAddedToBoard;
    }


    /**
     * remove a participant from the participants list
     * @param participant a participant that we want to remove
     * @return true if succeeded, false otherwise
     */
    public boolean removeSimulationParticipant(Participant participant){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered ParticipantsData.removeSimulationParticipant()");
        boolean isRemovedFromBoard = board.removeParticipantFromLocation(participant);
        boolean isRemovedFromData = simulationParticipants.remove(participant);
        return isRemovedFromBoard && isRemovedFromData;
    }

    /**
     * move a participant from one location to another
     * @param participant the participant that we want to move
     * @param newLocation the new location that we want to move the participant to
     * @return
     */
    public boolean moveParticipantToNewLocation(Participant participant, Location newLocation){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered ParticipantsData.moveParticipantToNewLocation()");

        boolean isRemovedFromBoard = board.removeParticipantFromLocation(participant);
        participant.setLocation(newLocation);
        boolean isAddedToBoardBoard = board.addParticipantToLocation(participant);

        return isRemovedFromBoard && isAddedToBoardBoard;
    }

    /**
     * invoke a iteration meaning passing on all the data and invoke and iteration method
     * @param scheduledExecutionTime
     */
    public void doIteration(long scheduledExecutionTime) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered ParticipantsData.doIteration()");

        // pass on all of the data collection and do one iteration
        for (IInvocable invocable: simulationParticipants) {
            invocable.updateCurrentScheduledExecutionTime(scheduledExecutionTime);
            invocable.iteration();
        }
    }

    /**
     * retrieves the board object of the simulation
     * @return the board object of the simulation
     */
    public Board getBoard() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered ParticipantsData.getBoard()");
        return board;
    }
}
