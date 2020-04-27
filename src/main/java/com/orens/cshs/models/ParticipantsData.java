package com.orens.cshs.models;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.strategy.StandardStrategy;

import java.util.Iterator;

public class ParticipantsData {
    private Board board;
    private final SimulationParticipantsList<IInvocable> simulationParticipants;

    public ParticipantsData() {
        this.board = new Board();
        this.simulationParticipants = new SimulationParticipantsList<>();
    }


    private void initParticipants() {

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

    public Iterator<IInvocable> getSimulationParticipantsIterator() {
        return simulationParticipants.iterator();
    }

    public boolean addSimulationParticipant(Participant participant){
        boolean isAddedToData = simulationParticipants.add(participant);
        boolean isAddedToBoard = board.addParticipantToLocation(participant);
        return isAddedToData && isAddedToBoard;
    }

    public boolean removeSimulationParticipant(Participant participant){
        boolean isRemovedFromBoard = board.removeParticipantFromLocation(participant);
        boolean isRemovedFromData = simulationParticipants.remove(participant);
        return isRemovedFromBoard && isRemovedFromData;
    }

    public boolean moveParticipantToNewLocation(Participant participant, Location newLocation){

        boolean isRemovedFromBoard = board.removeParticipantFromLocation(participant);
        participant.setLocation(newLocation);
        boolean isAddedToBoardBoard = board.addParticipantToLocation(participant);

        return isRemovedFromBoard && isAddedToBoardBoard;
    }

    public void doIteration(long scheduledExecutionTime) {
        // pass on all of the data collection and do one iteration
        for (IInvocable invocable: simulationParticipants) {
            invocable.updateCurrentScheduledExecutionTime(scheduledExecutionTime);
            invocable.iteration();
        }
    }

    public Board getBoard() {
        return board;
    }
}
