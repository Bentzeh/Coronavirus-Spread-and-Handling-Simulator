package com.orens.cshs.models;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.logic.observer.IInvocable;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private List<IInvocable> simulationParticipants;

    public Participants() {
        this.simulationParticipants = new ArrayList<>();

        initPersons();
    }

    private void initPersons() {

        int amountOfHealthyPeople = PropertiesFileReader.getInitialAmountOfHealthyPeople();
        for (int i = 0; i < amountOfHealthyPeople; ++i) {
            Person p = new Person();
            simulationParticipants.add(p);
            p.startSimulation();
        }

//        int amountOfCarryingPeople = PropertiesFileReader.getInitialAmountOfCarryingPeople();
//        for (int i = 0; i < amountOfCarryingPeople; ++i) {
//            Person p = new Person();
//            simulationParticipants.add(p);
//        }
//
//        int amountOfSickPeople = PropertiesFileReader.getInitialAmountOfSickPeople();
//        for (int i = 0; i < amountOfSickPeople; ++i) {
//            Person p = new Person();
//            simulationParticipants.add(p);
//        }
//
//        int amountOfInspectors = PropertiesFileReader.getInitialAmountOfInspectors();
//        for (int i = 0; i < amountOfInspectors; ++i) {
//            Person p = new Person();
//            simulationParticipants.add(p);
//        }
    }

    public void doIteration() {
        // pass on all of the data collection and and do one iteration
        for (IInvocable invocable: simulationParticipants) {
            invocable.iteration();
        }
    }
}
