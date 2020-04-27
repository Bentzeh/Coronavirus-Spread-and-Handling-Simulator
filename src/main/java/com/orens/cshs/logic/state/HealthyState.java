package com.orens.cshs.logic.state;

public class HealthyState extends AbstractHealthState{


    public HealthyState() {
        super(State.Healthy, SICK_TEMPERATURE_THRESHOLD-1);
    }

//    @Override
//    public boolean isChangeState(NormalPerson normalPerson, Participant otherParticipant) {
//        boolean inRange = Location.isWithinRadius(normalPerson.getLocation(), otherParticipant.getLocation(), CONTAGIOUS_RADIUS);
//
//        TimeFrame participantTimePassedFromLastLocationChange      = normalPerson.getTimeFrameOfLastLocationChangeTillNow();
//        TimeFrame otherParticipantTimePassedFromLastLocationChange = otherParticipant.getTimeFrameOfLastLocationChangeTillNow();
//        long overlappingTime = TimeFrame.getOverlappingTime(participantTimePassedFromLastLocationChange, otherParticipantTimePassedFromLastLocationChange);
//        boolean timeOverlap = overlappingTime < CONTAGIOUS_TIME ;
//
//        return  inRange && timeOverlap && RandomGenerator.trueWith80PercentProbability();
//    }
//
//    @Override
//    public boolean isChangeState(InspectorPerson inspectorPerson, Participant otherParticipant) {
//
//        AbstractHealthState otherParticipantState = otherParticipant.getCurrentHealthState();
//        int amountOfPeopleMet = inspectorPerson.getAmountOfPeopleMet();
//        if (otherParticipantState.isSick()){
//            SickState sickState = (SickState) otherParticipantState;
//            sickState.setIsolation();
//            inspectorPerson.setAmountOfPeopleMet(amountOfPeopleMet+1);
//        }
//        if (inspectorPerson.isMetToMuchPeople()){
//            inspectorPerson.setAmountOfPeopleMet(0);
//            return true;
//        }
//        return false;
//    }
}
