package com.orens.cshs.logic.state;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.TimerUtils;

public class SickState extends AbstractHealthState{

    protected static final long ISOLATION_TIME = PropertiesFileReader.getIsolationTimeInMilliseconds();

    protected long startIsolationTimestamp;
    protected boolean isIsolated;

    public SickState() {
        super(State.Sick, SICK_TEMPERATURE_THRESHOLD+1);

        this.startIsolationTimestamp = -1L;
        this.isIsolated = false;
    }


    public void setIsolation(){
        startIsolationTimestamp = TimerUtils.getCurrentTimeStampAsRawLongFromSystem();
        isIsolated = true;
    }

    public boolean isIsolationTimeOver(){
        if (TimerUtils.getMillisTimeGapInSecondsFromNow(startIsolationTimestamp) >= ISOLATION_TIME){
            isIsolated = false;
            return true;
        }
        return false;
    }

    public boolean isIsolated(){
        return isIsolated;
    }


//    @Override
//    public boolean isChangeState(NormalPerson normalPerson, Participant otherParticipant) {
//        return isChangeState(normalPerson);
//    }
//
//    @Override
//    public boolean isChangeState(InspectorPerson inspectorPerson, Participant otherParticipant) {
//        return isChangeState(inspectorPerson);
//    }
//
//    private boolean isChangeState(Participant participant){
//        TimeFrame timeFrame = participant.getCurrentHealthState().getTimeFrameOfStateChangedTillNow();
//        long period = timeFrame.getPeriodInSeconds();
//
//        boolean trueWith40Percent = RandomGenerator.trueWith40PercentProbability();
//        boolean trueWith60Percent = RandomGenerator.trueWith60PercentProbability();
//        boolean trueWith80Percent = RandomGenerator.trueWith80PercentProbability();
//
//        return (    ((period == Seconds.One.getValue() || period == Seconds.Five.getValue()) && trueWith40Percent) ||
//                ((period == Seconds.Two.getValue() || period == Seconds.Four.getValue()) && trueWith60Percent) ||
//                (period == Seconds.Three.getValue() && trueWith80Percent) ||
//                (period >= Seconds.Six.getValue()));
//    }
}