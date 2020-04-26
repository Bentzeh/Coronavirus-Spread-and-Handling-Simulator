package com.orens.cshs.logic.state;

import com.orens.cshs.infra.utils.RandomGenerator;
import com.orens.cshs.models.InspectorPerson;
import com.orens.cshs.models.Participant;
import com.orens.cshs.models.pojos.Seconds;
import com.orens.cshs.models.pojos.TimeFrame;


public class CarryingState extends AbstractHealthState {

    public CarryingState() {
        super(System.currentTimeMillis(), SICK_TEMPERATURE_THRESHOLD - 1, State.Carrying);
    }

    @Override
    public boolean changeState(Participant participant, Participant otherParticipant) {
        return activateRules(participant, otherParticipant);
    }

    @Override
    public boolean changeState(InspectorPerson inspectorPerson, Participant otherParticipant) {
        return activateRules(inspectorPerson, otherParticipant);
    }

    @Override
    public boolean isIsolated() {
        return false;
    }

    private boolean activateRules(Participant participant, Participant otherParticipant){
        TimeFrame sicknessTimeFrame = this.getTimeFrameOfStateChangedTillNow();
        long sicknessPeriod = sicknessTimeFrame.getPeriodInSeconds();

        boolean trueWith40Percent = RandomGenerator.trueWith40PercentProbability();
        boolean trueWith60Percent = RandomGenerator.trueWith60PercentProbability();
        boolean trueWith80Percent = RandomGenerator.trueWith80PercentProbability();

        if (((sicknessPeriod == Seconds.One.getValue() || sicknessPeriod == Seconds.Five.getValue()) && trueWith40Percent) ||
            ((sicknessPeriod == Seconds.Two.getValue() || sicknessPeriod == Seconds.Four.getValue()) && trueWith60Percent) ||
            (sicknessPeriod == Seconds.Three.getValue() && trueWith80Percent)){


            participant.setCurrentHealthState(new SickState());
            return true;
        }
        return false;
    }

}
