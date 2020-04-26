package com.orens.cshs.logic.state;

import com.orens.cshs.models.InspectorPerson;
import com.orens.cshs.models.Participant;

public abstract class AbstractHealthState {

    abstract public boolean changeState(Participant participant, Participant otherParticipant);
    abstract public boolean changeState(InspectorPerson inspectorPerson, Participant otherParticipant);


}
