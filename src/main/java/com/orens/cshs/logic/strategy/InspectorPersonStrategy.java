package com.orens.cshs.logic.strategy;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.RandomGenerator;
import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.HealthState;
import com.orens.cshs.models.Board;
import com.orens.cshs.models.InspectorPerson;
import com.orens.cshs.models.Participant;
import com.orens.cshs.models.ParticipantsData;
import com.orens.cshs.models.pojos.Location;
import com.orens.cshs.models.pojos.Seconds;
import com.orens.cshs.models.pojos.TimeFrame;

import java.util.Iterator;

public class InspectorPersonStrategy extends AbstractLogicStrategy{


    protected static final long INSPECTOR_SICK_THRESHOLD = PropertiesFileReader.getInspectorSickThreshold();

    public InspectorPersonStrategy(Board board, ParticipantsData participantsData) {
        super(board, participantsData);
    }

    @Override
    public void executeLogic(Participant participant) {
        HealthState participantState = participant.getCurrentHealthState();
        if (!participantState.isIsolated()){
            // 1. generate random location
            Location newLocation = getNextRandomLocation(participant.getLocation());

            // 2. set new location on participant and update board on new location
            board.removeParticipantFromLocation(participant);
            participant.setLocation(newLocation);
            board.addParticipantToLocation(participant);

            if (participantState.isHealthy()){
                Iterator<IInvocable> listOfSimulationParticipantsIterator = participantsData.getSimulationParticipantsIterator();
                boolean isFinish = false;
                while (listOfSimulationParticipantsIterator.hasNext() && !isFinish) {
                    Participant otherParticipant = (Participant)listOfSimulationParticipantsIterator.next();

                    isFinish = healthState((InspectorPerson)participant, otherParticipant);
                }
            }
            else if (participantState.isCarrying()){
                boolean isChanged = isStateChangeable(participant, HealthState.State.Sick);
            }
            else { // if (participantState.isSick()){
                boolean isChanged = isStateChangeable(participant, HealthState.State.Dead);
//                if (isChanged){
//                    board.removeParticipantFromLocation(participant);
//                    participantsData.removeSimulationParticipant(participant);
//                }
            }
        }
        else {
            if (participantState.isIsolationTimeOver()){
                participant.setCurrentHealthState(HealthState.State.Healthy);
            }
        }
    }

    public boolean healthState(InspectorPerson inspectorPerson, Participant otherParticipant) {
        HealthState otherParticipantState = otherParticipant.getCurrentHealthState();
        int amountOfPeopleMet = inspectorPerson.getAmountOfPeopleMet();
        if (otherParticipantState.isSick()){
            otherParticipantState.setIsolation();
            inspectorPerson.setAmountOfPeopleMet(amountOfPeopleMet+1);
        }
        if (amountOfPeopleMet > INSPECTOR_SICK_THRESHOLD){
            inspectorPerson.setAmountOfPeopleMet(0);
            inspectorPerson.setCurrentHealthState(HealthState.State.Carrying);
            return true;
        }
        return false;
    }

    public boolean isStateChangeable(Participant participant, HealthState.State nextState) {
        TimeFrame timeFrame = participant.getCurrentHealthState().getTimeFrameOfStateChangedTillNow();
        long period = timeFrame.getPeriodInSeconds();

        boolean trueWith40Percent = RandomGenerator.trueWith40PercentProbability();
        boolean trueWith60Percent = RandomGenerator.trueWith60PercentProbability();
        boolean trueWith80Percent = RandomGenerator.trueWith80PercentProbability();

        if (((period == Seconds.One.getValue() || period == Seconds.Five.getValue()) && trueWith40Percent) ||
                ((period == Seconds.Two.getValue() || period == Seconds.Four.getValue()) && trueWith60Percent) ||
                (period == Seconds.Three.getValue() && trueWith80Percent) ||
                (period >= Seconds.Six.getValue())){

            participant.setCurrentHealthState(nextState);
            return true;
        }
        return false;
    }

}
