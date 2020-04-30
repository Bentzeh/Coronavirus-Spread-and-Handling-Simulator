package com.orens.cshs.logic.strategy;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.RandomGenerator;
import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.state.CarryingState;
import com.orens.cshs.logic.state.HealthyState;
import com.orens.cshs.logic.state.SickState;
import com.orens.cshs.models.*;

import java.util.Iterator;

/**
 * an implementation of the abstract strategy
 */
public class StandardStrategy extends AbstractLogicStrategy {

    public StandardStrategy(ParticipantsData participantsData) {
        super(participantsData);
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered StandardStrategy.Constructor()");
    }

    /**
     * the logic that a normalPerson should activate on each iteration
     * @param normalPerson the participant that should activate this method logic
     */
    @Override
    public void executeLogic(NormalPerson normalPerson) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered StandardStrategy.executeLogic(normalPerson)");

        AbstractHealthState normalPersonState = normalPerson.getCurrentHealthState();


        if (normalPersonState.isSick()){
            SickState sickState = (SickState) normalPersonState;

            if (sickState.isIsolated()){
                if (sickState.isIsolationTimeOver()){
                    LoggerHandler.getInstance().log(ReportLevel.DEBUG,""+normalPerson.getId() + "  changed state to |Healthy|");
                    normalPerson.setCurrentHealthState(AbstractHealthState.State.Healthy);
                }
                // else in Isolation: does nothing
            }
            else { // sick but not isolated can move

                step(normalPerson);

                if (isChangeState(normalPerson)){
                    LoggerHandler.getInstance().log(ReportLevel.DEBUG,""+normalPerson.getId() + "  changed state to |Dead|");
                    participantsData.removeSimulationParticipant(normalPerson);//Dead
                }
            }
        }
        else if (normalPersonState.isCarrying()){

            step(normalPerson);

            if (isChangeState(normalPerson)){
                LoggerHandler.getInstance().log(ReportLevel.DEBUG,""+normalPerson.getId() + "  changed state to |Sick|");
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

                boolean isOtherSick = otherParticipant.getCurrentHealthState().isSick();

                if  (isOtherSick && inRange && timeOverlap && RandomGenerator.trueWith80PercentProbability()){
                    LoggerHandler.getInstance().log(ReportLevel.DEBUG,""+normalPerson.getId() + "  changed state to |Carrying|");
                    normalPerson.setCurrentHealthState(AbstractHealthState.State.Carrying);
                    isFinish = true;
                }
            }
        }
    }

    /**
     * the logic that a inspectorPerson should activate on each iteration
     * @param inspectorPerson the participant that should activate this method logic
     */
    @Override
    public void executeLogic(InspectorPerson inspectorPerson) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered StandardStrategy.executeLogic(inspectorPerson)");

        AbstractHealthState inspectorPersonState = inspectorPerson.getCurrentHealthState();


        if (inspectorPersonState.isSick()){
            SickState sickState = (SickState) inspectorPersonState;
            if (sickState.isIsolated()){
                if (sickState.isIsolationTimeOver()){
                    LoggerHandler.getInstance().log(ReportLevel.DEBUG,""+inspectorPerson.getId() + "  changed state to |Healthy|");
                    inspectorPerson.setCurrentHealthState(AbstractHealthState.State.Healthy);
                }
                // else in Isolation: does nothing
            }
            else { // sick but not isolated can move

                step(inspectorPerson);

                if (isChangeState(inspectorPerson)){
                    LoggerHandler.getInstance().log(ReportLevel.DEBUG,""+inspectorPerson.getId() + "  changed state to |Dead|");
                    participantsData.removeSimulationParticipant(inspectorPerson);//Dead
                }
            }
        }
        else if (inspectorPersonState.isCarrying()){
            CarryingState carryingState = (CarryingState) inspectorPersonState;

            step(inspectorPerson);
            if (isChangeState(inspectorPerson)){
                inspectorPerson.setCurrentHealthState(AbstractHealthState.State.Sick);
            }
        }
        else{ // if (inspectorPersonState.isHealthy()){
            HealthyState healthyState = (HealthyState) inspectorPersonState;

            step(inspectorPerson);

            Iterator<IInvocable> listOfSimulationParticipantsIterator = participantsData.getSimulationParticipantsIterator();
            boolean isFinish = false;
            while (listOfSimulationParticipantsIterator.hasNext() && !isFinish) {
                Participant otherParticipant = (Participant)listOfSimulationParticipantsIterator.next();


                AbstractHealthState otherParticipantState = otherParticipant.getCurrentHealthState();
                int amountOfPeopleMet = inspectorPerson.getAmountOfPeopleMet();

                boolean inRange = Location.isWithinRadius(inspectorPerson.getLocation(), otherParticipant.getLocation(), CONTAGIOUS_RADIUS);
                if (inRange && otherParticipantState.isSick() && !((SickState)otherParticipantState).isIsolated()){
                    LoggerHandler.getInstance().log(ReportLevel.DEBUG,""+inspectorPerson.getId() + "  is treating " + otherParticipant.getId());
                    SickState sickState = (SickState) otherParticipantState;
                    sickState.setIsolation();
                    inspectorPerson.setAmountOfPeopleMet(amountOfPeopleMet+1);
                }
                if (inspectorPerson.isMetToMuchPeople()){
                    inspectorPerson.setAmountOfPeopleMet(0);
                    LoggerHandler.getInstance().log(ReportLevel.DEBUG,""+inspectorPerson.getId() + "  changed state to |Carrying|");
                    inspectorPerson.setCurrentHealthState(AbstractHealthState.State.Carrying);
                    isFinish = true;
                }
            }
        }
    }

    /**
     * move a participant from one location to another
     * @param participant the participant that we want to move
     */
    private void step(Participant participant){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered StandardStrategy.step()");

        Location newLocation = getNextRandomLocation(participant.getLocation());
        participantsData.moveParticipantToNewLocation(participant, newLocation);
    }

    /**
     * checks if the participant state should be changed
     * @param participant the participant that we want to check
     * @return true if should be changed, false otherwise
     */
    private boolean isChangeState(Participant participant){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered StandardStrategy.isChangeState()");

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
