package com.orens.cshs.logic.strategy;

import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.models.Board;
import com.orens.cshs.models.Participant;
import com.orens.cshs.models.ParticipantsData;
import com.orens.cshs.models.pojos.Location;

import java.util.Iterator;

public class NormalPersonStrategy extends AbstractLogicStrategy {

    public NormalPersonStrategy(Board board, ParticipantsData participantsData) {
        super(board, participantsData);
    }

    @Override
    public void executeLogic(Participant participant) {

        //if (participant.getCurrentHealthState().isIsolated()) { if he is isolated
        if (true) {
            // 1. generate random location
            Location newLocation = getNextRandomLocation(participant.getLocation());

            // 2. set new location on participant and update board on new location
            board.removeParticipantFromLocation(participant);
            if (!participant.getLocation().equals(newLocation)){ //sameSpot
                participant.setLocation(newLocation);
            }
            board.addParticipantToLocation(participant);
        }


        // if im healthy or inspector otherwise just calculate time
        // 3. invoke  logic
        Iterator<IInvocable> listOfSimulationParticipantsIterator = participantsData.getSimulationParticipantsIterator();
        boolean isFinish = false;
        while (listOfSimulationParticipantsIterator.hasNext() && !isFinish) {
            Participant otherParticipant = (Participant)listOfSimulationParticipantsIterator.next();
            // check if he is near me
            isFinish = participant.getCurrentHealthState().changeState(participant, otherParticipant);
        }

    }

//    public void executeLogic(InspectorPerson participant) {
//
//        //if (participant.getCurrentHealthState().isMovable()) { if he is isolated
//        if (true) {
//            // 1. generate random location
//            Location newLocation = getNextRandomLocation(participant.getLocation());
//
//            // 2. set new location on participant and update board on new location
//            board.removeParticipantFromLocation(participant);
//            if (!participant.getLocation().equals(newLocation)){ //sameSpot
//                participant.setLocation(newLocation);
//            }
//            board.addParticipantToLocation(participant);
//        }
//
//        // 3. invoke  logic
//        Iterator<IInvocable> listOfSimulationParticipantsIterator = participantsData.getSimulationParticipantsIterator();
//        boolean isFinish = false;
//        while (listOfSimulationParticipantsIterator.hasNext() && !isFinish) {
//            Participant otherParticipant = (Participant)listOfSimulationParticipantsIterator.next();
//            // check if he is near me
//            isFinish = participant.getCurrentHealthState().changeState(participant, otherParticipant);
//        }
//
//    }


}

