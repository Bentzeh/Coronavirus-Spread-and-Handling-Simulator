package com.orens.cshs.logic.strategy;

import com.orens.cshs.models.Board;
import com.orens.cshs.models.Participant;
import com.orens.cshs.models.ParticipantsData;

public class InspectorPersonStrategy extends AbstractLogicStrategy{

    public InspectorPersonStrategy(Board board, ParticipantsData participantsData) {
        super(board, participantsData);
    }

    @Override
    public void executeLogic(Participant participant) {

    }
}
