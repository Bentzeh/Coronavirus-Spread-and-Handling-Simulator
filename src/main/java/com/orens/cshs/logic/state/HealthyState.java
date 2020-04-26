package com.orens.cshs.logic.state;

import com.orens.cshs.models.InspectorPerson;
import com.orens.cshs.models.Participant;


public class HealthyState extends AbstractHealthState {

    @Override
    public boolean changeState(Participant participant, Participant otherParticipant) {
        return activateRules(participant, otherParticipant);
    }

    @Override
    public boolean changeState(InspectorPerson inspectorPerson, Participant otherParticipant) {
        return activateRules(inspectorPerson, otherParticipant);
    }

    private boolean activateRules(Participant participant, Participant otherParticipant){
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        return true;
    }
}
