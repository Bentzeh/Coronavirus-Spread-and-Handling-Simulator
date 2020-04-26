package com.orens.cshs.logic.state;

import com.orens.cshs.models.InspectorPerson;
import com.orens.cshs.models.Participant;

public class DeadState extends AbstractHealthState {

    public DeadState() {
        super(System.currentTimeMillis(), INSPECTOR_SICK_THRESHOLD + 1);
    }

    @Override
    public boolean changeState(Participant participant, Participant otherParticipant) {
        return false;
    }

    @Override
    public boolean changeState(InspectorPerson inspectorPerson, Participant otherParticipant) {
        return false;
    }
}
