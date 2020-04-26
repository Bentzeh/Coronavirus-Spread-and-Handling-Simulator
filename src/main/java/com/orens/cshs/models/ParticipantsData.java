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


    public void doIteration(long scheduledExecutionTime) {
        // pass on all of the data collection and do one iteration
        for (IInvocable invocable: simulationParticipants) {
            invocable.updateTimePassedFromLastLocationChange(scheduledExecutionTime);
            invocable.iteration();
        }
    }


}
