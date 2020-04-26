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

        //if (participant.getCurrentHealthState().isMovable()) {
        if (true) {
            // 1. generate random location
            Location newLocation = getNextRandomLocation(participant.getLocation());

            // 2. set new location on participant and update board on new location
            board.removeParticipantFromLocation(participant);
            participant.setLocation(newLocation);
            board.addParticipantToLocation(participant);
        }

        Iterator<IInvocable> listOfSimulationParticipantsIterator = participantsData.getSimulationParticipantsIterator();
        boolean isFinish = false;
        while (listOfSimulationParticipantsIterator.hasNext() && !isFinish) {
            Participant otherParticipant = (Participant)listOfSimulationParticipantsIterator.next();

            isFinish = participant.getCurrentHealthState().changeState(participant, otherParticipant);
        }

    }


}

