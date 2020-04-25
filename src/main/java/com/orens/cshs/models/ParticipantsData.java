package com.orens.cshs.models;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.logic.observer.IInvocable;

import java.util.ArrayList;
import java.util.List;

public class ParticipantsData {

    private List<IInvocable> simulationParticipants;

    private Board board;

    public ParticipantsData(Board board) {
        this.simulationParticipants = new ArrayList<>();
        this.board = board;
        initPersons();
    }

    private void initPersons() {

        int amountOfHealthyPeople = PropertiesFileReader.getInitialAmountOfHealthyPeople();
        for (int i = 0; i < amountOfHealthyPeople; ++i) {
            Person p = new Person(board);
            simulationParticipants.add(p);
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
        // pass on all of the data collection and do one iteration
        for (IInvocable invocable: simulationParticipants) {
            invocable.iteration();
        }
    }
}
