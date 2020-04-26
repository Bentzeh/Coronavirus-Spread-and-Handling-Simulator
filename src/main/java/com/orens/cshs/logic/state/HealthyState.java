package com.orens.cshs.logic.state;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.RandomGenerator;
import com.orens.cshs.models.InspectorPerson;
import com.orens.cshs.models.Participant;
import com.orens.cshs.models.pojos.Location;
import com.orens.cshs.models.pojos.TimeFrame;


public class HealthyState extends AbstractHealthState {

    private static final long CONTAGIOUS_TIME = PropertiesFileReader.getContagiousTimeInMilliseconds();


    public HealthyState() {
        super(System.currentTimeMillis(), SICK_TEMPERATURE_THRESHOLD - 1, State.Healthy);
    }

    @Override
    public boolean changeState(Participant participant, Participant otherParticipant) {
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

    @Override
    public boolean changeState(InspectorPerson inspectorPerson, Participant otherParticipant) {
        if (inspectorPerson.getLocation().equals(otherParticipant.getLocation())){
            int amountOfPeopleMet = inspectorPerson.getAmountOfPeopleMet();
            if (otherParticipant.getCurrentHealthState().bodyHeat >= SICK_TEMPERATURE_THRESHOLD){
                // move to isolation (in sick state)
                inspectorPerson.setAmountOfPeopleMet(amountOfPeopleMet+1);
            }
            if (amountOfPeopleMet > INSPECTOR_SICK_THRESHOLD){
                inspectorPerson.setAmountOfPeopleMet(0);
                inspectorPerson.setCurrentHealthState(new CarryingState());
                return true;
            }
        }
        return false;
    }

//    private boolean activateRules(Participant participant, Participant otherParticipant){
//        boolean inRange = Location.isWithinRadius(participant.getLocation(), otherParticipant.getLocation(), CONTAGIOUS_RADIUS);
//
//        TimeFrame participantTimePassedFromLastLocationChange      = participant.getTimeFrameOfLastLocationChangeTillNow();
//        TimeFrame otherParticipantTimePassedFromLastLocationChange = otherParticipant.getTimeFrameOfLastLocationChangeTillNow();
//        long overlappingTime = TimeFrame.getOverlappingTime(participantTimePassedFromLastLocationChange, otherParticipantTimePassedFromLastLocationChange);
//        boolean timeOverlap = overlappingTime < CONTAGIOUS_TIME ;
//
//        if (inRange && timeOverlap && RandomGenerator.trueWith80PercentProbability()){
//            participant.setCurrentHealthState(new CarryingState());
//            return true;
//        }
//        return false;
//    }
}
