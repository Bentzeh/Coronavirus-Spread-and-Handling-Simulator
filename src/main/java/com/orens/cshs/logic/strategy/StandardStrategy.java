package com.orens.cshs.logic.strategy;

import com.orens.cshs.infra.utils.RandomGenerator;
import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.state.CarryingState;
import com.orens.cshs.logic.state.HealthyState;
import com.orens.cshs.logic.state.SickState;
import com.orens.cshs.models.*;

import java.util.Iterator;

public class StandardStrategy extends AbstractLogicStrategy {

    public StandardStrategy(ParticipantsData participantsData) {
        super(participantsData);
    }


    @Override
    public void executeLogic(NormalPerson normalPerson) {

        AbstractHealthState normalPersonState = normalPerson.getCurrentHealthState();


        if (normalPersonState.isSick()){
            SickState sickState = (SickState) normalPersonState;

            if (sickState.isIsolated()){
                if (sickState.isIsolationTimeOver()){
                    normalPerson.setCurrentHealthState(AbstractHealthState.State.Healthy);
                }
                // else in Isolation: does nothing
            }
            else { // sick but not isolated can move

                step(normalPerson);

                if (isChangeState(normalPerson)){
                    participantsData.removeSimulationParticipant(normalPerson);//dead
                }
            }
        }
        else if (normalPersonState.isCarrying()){

            step(normalPerson);

            if (isChangeState(normalPerson)){
                normalPerson.setCurrentHealthState(AbstractHealthState.State.Sick);
            }
        }
        else{ // if (normalPersonState.isHealthy()){

            step(normalPerson);

            Iterator<IInvocable> listOfSimulationParticipantsIterator = participantsData.getSimulationParticipantsIterator();
            boolean isFinish = false;
            while (listOfSimulationParticipantsIterator.hasNext() && !isFinish) {
                Participant otherParticipant = (Participant)listOfSimulationParticipantsIterator.next();

                boolean inRange = Location.isWithinRadius(normalPerson.getLocation(), otherParticipant.getLocation(), CONTAGIOUS_RADIUS);

                TimeFrame participantTimePassedFromLastLocationChange      = normalPerson.getTimeFrameOfLastLocationChangeTillNow();
                TimeFrame otherParticipantTimePassedFromLastLocationChange = otherParticipant.getTimeFrameOfLastLocationChangeTillNow();
                long overlappingTime = TimeFrame.getOverlappingTime(participantTimePassedFromLastLocationChange, otherParticipantTimePassedFromLastLocationChange);
                boolean timeOverlap = overlappingTime < CONTAGIOUS_TIME ;

                if  (inRange && timeOverlap && RandomGenerator.trueWith80PercentProbability()){
                    normalPerson.setCurrentHealthState(AbstractHealthState.State.Carrying);
                    isFinish = true;
                }
            }
        }
    }

    @Override
    public void executeLogic(InspectorPerson inspectorPerson) {

        AbstractHealthState normalPersonState = inspectorPerson.getCurrentHealthState();


        if (normalPersonState.isSick()){
            SickState sickState = (SickState) normalPersonState;
            if (sickState.isIsolated()){
                if (sickState.isIsolationTimeOver()){
                    inspectorPerson.setCurrentHealthState(AbstractHealthState.State.Healthy);
                }
                // else in Isolation: does nothing
            }
            else { // sick but not isolated can move

                step(inspectorPerson);

                if (isChangeState(inspectorPerson)){
                    participantsData.removeSimulationParticipant(inspectorPerson);//dead
                }
            }
        }
        else if (normalPersonState.isCarrying()){
            CarryingState carryingState = (CarryingState) normalPersonState;

            step(inspectorPerson);
            if (isChangeState(inspectorPerson)){
                inspectorPerson.setCurrentHealthState(AbstractHealthState.State.Sick);
            }
        }
        else{ // if (normalPersonState.isHealthy()){
            HealthyState healthyState = (HealthyState) normalPersonState;

            step(inspectorPerson);

            Iterator<IInvocable> listOfSimulationParticipantsIterator = participantsData.getSimulationParticipantsIterator();
            boolean isFinish = false;
            while (listOfSimulationParticipantsIterator.hasNext() && !isFinish) {
                Participant otherParticipant = (Participant)listOfSimulationParticipantsIterator.next();


                AbstractHealthState otherParticipantState = otherParticipant.getCurrentHealthState();
                int amountOfPeopleMet = inspectorPerson.getAmountOfPeopleMet();
                if (otherParticipantState.isSick()){
                    SickState sickState = (SickState) otherParticipantState;
                    sickState.setIsolation();
                    inspectorPerson.setAmountOfPeopleMet(amountOfPeopleMet+1);
                }
                if (inspectorPerson.isMetToMuchPeople()){
                    inspectorPerson.setAmountOfPeopleMet(0);
                    inspectorPerson.setCurrentHealthState(AbstractHealthState.State.Carrying);
                    isFinish = true;
                }
            }
        }
    }


    private void step(Participant participant){
        Location newLocation = getNextRandomLocation(participant.getLocation());
        participantsData.moveParticipantToNewLocation(participant, newLocation);
    }

    private boolean isChangeState(Participant participant){
        TimeFrame timeFrame = participant.getCurrentHealthState().getTimeFrameOfStateChangedTillNow();
        long period = timeFrame.getPeriodInSeconds();

        boolean trueWith40Percent = RandomGenerator.trueWith40PercentProbability();
        boolean trueWith60Percent = RandomGenerator.trueWith60PercentProbability();
        boolean trueWith80Percent = RandomGenerator.trueWith80PercentProbability();

        return (    ((period == Seconds.One.getValue() || period == Seconds.Five.getValue()) && trueWith40Percent) ||
                    ((period == Seconds.Two.getValue() || period == Seconds.Four.getValue()) && trueWith60Percent) ||
                    (period == Seconds.Three.getValue() && trueWith80Percent) ||
                    (period >= Seconds.Six.getValue()));
    }


}
