package com.orens.cshs.logic.strategy;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.RandomGenerator;
import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.state.CarryingState;
import com.orens.cshs.models.Board;
import com.orens.cshs.models.Participant;
import com.orens.cshs.models.ParticipantsData;
import com.orens.cshs.models.pojos.Location;
import com.orens.cshs.models.pojos.TimeFrame;

import java.util.Iterator;

public class NormalPersonStrategy extends AbstractLogicStrategy {

    public NormalPersonStrategy(Board board, ParticipantsData participantsData) {
        super(board, participantsData);
    }


    private static final long   CONTAGIOUS_TIME            = PropertiesFileReader.getContagiousTimeInMilliseconds();
    protected static final int  INSPECTOR_SICK_THRESHOLD   = PropertiesFileReader.getInspectorSickThreshold();;
    protected static final int  SICK_TEMPERATURE_THRESHOLD = PropertiesFileReader.getSickTemperatureThreshold();
    protected static final int  CONTAGIOUS_RADIUS          = PropertiesFileReader.getContagiousRadius();
    protected static final long ISOLATION_TIME             = PropertiesFileReader.getIsolationTimeInMilliseconds();
    /// move to abst

    @Override
    public void executeLogic(Participant participant) {
        AbstractHealthState participantState = participant.getCurrentHealthState();
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
                    isFinish = HealthState(participant, otherParticipant);
                    //isFinish = participant.getCurrentHealthState().changeState(participant, otherParticipant);
                }
            }
            else {
                // calculate change state with probabilities (Carry,Sick)
            }
        }
        else {
            // check if isolation time over and change state
            if (!participantState.updateAndReportIsolationTime()){
                //change state to healthy
            }
        }
    }


    public boolean HealthState(Participant participant, Participant otherParticipant) {
        boolean inRange = Location.isWithinRadius(participant.getLocation(), otherParticipant.getLocation(), CONTAGIOUS_RADIUS);

        TimeFrame participantTimePassedFromLastLocationChange      = participant.getTimeFrameOfLastLocationChangeTillNow();
        TimeFrame otherParticipantTimePassedFromLastLocationChange = otherParticipant.getTimeFrameOfLastLocationChangeTillNow();
        long overlappingTime = TimeFrame.getOverlappingTime(participantTimePassedFromLastLocationChange, otherParticipantTimePassedFromLastLocationChange);
        boolean timeOverlap = overlappingTime < CONTAGIOUS_TIME ;

        if (inRange && timeOverlap && RandomGenerator.trueWith80PercentProbability()){
            participant.setCurrentHealthState(new CarryingState());
            return true;
        }
        return false;
    }

//    public boolean CarryingState(Participant participant, Participant otherParticipant) {
//        TimeFrame timeFrame = participantState.getTimeFrameOfStateChangedTillNow();
//        long sicknessPeriod = timeFrame.getPeriodInSeconds();
//
//        boolean trueWith40Percent = RandomGenerator.trueWith40PercentProbability();
//        boolean trueWith60Percent = RandomGenerator.trueWith60PercentProbability();
//        boolean trueWith80Percent = RandomGenerator.trueWith80PercentProbability();
//
//        if (((sicknessPeriod == Seconds.One.getValue() || sicknessPeriod == Seconds.Five.getValue()) && trueWith40Percent) ||
//                ((sicknessPeriod == Seconds.Two.getValue() || sicknessPeriod == Seconds.Four.getValue()) && trueWith60Percent) ||
//                (sicknessPeriod == Seconds.Three.getValue() && trueWith80Percent)){
//
//
//            participant.setCurrentHealthState(new SickState());
//            return true;
//        }
//        return false;
//    }










//
//    private boolean changeState(Participant participant, AbstractHealthState participantState){
//        TimeFrame stateTimeFrame = participantState.getTimeFrameOfStateChangedTillNow();
//        long sicknessPeriod = stateTimeFrame.getPeriodInSeconds();
//
//        boolean trueWith40Percent = RandomGenerator.trueWith40PercentProbability();
//        boolean trueWith60Percent = RandomGenerator.trueWith60PercentProbability();
//        boolean trueWith80Percent = RandomGenerator.trueWith80PercentProbability();
//
//        return (((sicknessPeriod == Seconds.One.getValue() || sicknessPeriod == Seconds.Five.getValue()) && trueWith40Percent) ||
//                ((sicknessPeriod == Seconds.Two.getValue() || sicknessPeriod == Seconds.Four.getValue()) && trueWith60Percent) ||
//                (sicknessPeriod == Seconds.Three.getValue() && trueWith80Percent));
//            //participant.setCurrentHealthState(new DeadState());
//    }
//
//
//    public void executeLogic2(Participant participant) {
//
//        //if (participant.getCurrentHealthState().isIsolated()) { if he is isolated
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
//
//        // if im healthy or inspector otherwise just calculate time
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



//    private void changeState(Participant participant, CarryingState participantState){
//        TimeFrame sicknessTimeFrame = participantState.getTimeFrameOfStateChangedTillNow();
//        long sicknessPeriod = sicknessTimeFrame.getPeriodInSeconds();
//
//        boolean trueWith40Percent = RandomGenerator.trueWith40PercentProbability();
//        boolean trueWith60Percent = RandomGenerator.trueWith60PercentProbability();
//        boolean trueWith80Percent = RandomGenerator.trueWith80PercentProbability();
//
//        if (((sicknessPeriod == Seconds.One.getValue() || sicknessPeriod == Seconds.Five.getValue()) && trueWith40Percent) ||
//                ((sicknessPeriod == Seconds.Two.getValue() || sicknessPeriod == Seconds.Four.getValue()) && trueWith60Percent) ||
//                (sicknessPeriod == Seconds.Three.getValue() && trueWith80Percent)){
//
//            participant.setCurrentHealthState(new SickState());
//        }
//    }
//
//    private void changeState(Participant participant, SickState participantState){
//        TimeFrame sicknessTimeFrame = participantState.getTimeFrameOfStateChangedTillNow();
//        long sicknessPeriod = sicknessTimeFrame.getPeriodInSeconds();
//
//        boolean trueWith40Percent = RandomGenerator.trueWith40PercentProbability();
//        boolean trueWith60Percent = RandomGenerator.trueWith60PercentProbability();
//        boolean trueWith80Percent = RandomGenerator.trueWith80PercentProbability();
//
//        if (((sicknessPeriod == Seconds.One.getValue() || sicknessPeriod == Seconds.Five.getValue()) && trueWith40Percent) ||
//                ((sicknessPeriod == Seconds.Two.getValue() || sicknessPeriod == Seconds.Four.getValue()) && trueWith60Percent) ||
//                (sicknessPeriod == Seconds.Three.getValue() && trueWith80Percent)){
//
//            participant.setCurrentHealthState(new DeadState());
//        }
//    }


}

