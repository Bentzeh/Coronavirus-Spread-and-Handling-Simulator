package com.orens.cshs.models;

import com.orens.cshs.logic.observer.IInvocable;

import java.util.Iterator;

public class ParticipantsData {

    private final SimulationParticipantsList<IInvocable> simulationParticipants;

    public ParticipantsData() {
        this.simulationParticipants = new SimulationParticipantsList<>();
    }

    public Iterator<IInvocable> getSimulationParticipantsIterator() {
        return simulationParticipants.iterator();
    }

    public boolean addSimulationParticipant(IInvocable element){
        return simulationParticipants.add(element);
    }


    public void doIteration() { // TODO: 26/04/2020 add time to method argument
        // pass on all of the data collection and do one iteration
        for (IInvocable invocable: simulationParticipants) {
            invocable.iteration();
            invocable.updateTimePassedFromLastLocationChange(); // TODO: 26/04/2020 add time to method argument
        }
    }
}
