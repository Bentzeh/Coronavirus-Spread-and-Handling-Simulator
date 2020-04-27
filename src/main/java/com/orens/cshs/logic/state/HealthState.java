package com.orens.cshs.logic.state;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.TimerUtils;
import com.orens.cshs.models.pojos.TimeFrame;

public class HealthState {

    protected static final int  SICK_TEMPERATURE_THRESHOLD = PropertiesFileReader.getSickTemperatureThreshold();
    protected static final long ISOLATION_TIME             = PropertiesFileReader.getIsolationTimeInMilliseconds();

    public enum State {Healthy, Carrying, Sick, Dead;}
    protected State currentState;

    protected long startIsolationTimestamp;
    protected long stateChangedTimeStamp;

    protected int bodyHeat;
    protected boolean isIsolated;


    public HealthState() {

        this.currentState = State.Healthy;

        this.startIsolationTimestamp = -1L;
        this.isIsolated = false;
        setBodyHeat();
    }

    private void setBodyHeat(){
        switch (currentState){
            case Sick:
            case Dead:
                this.bodyHeat = SICK_TEMPERATURE_THRESHOLD+1;
                break;
            case Carrying:
            default:
                this.bodyHeat = SICK_TEMPERATURE_THRESHOLD-1;
                break;
        }
    }

    public void setStateWithTimeStamp(State currentHealthState) {
        this.currentState = currentHealthState;
        this.stateChangedTimeStamp = TimerUtils.getCurrentTimeStampAsRawLongFromSystem();
    }

    public TimeFrame getTimeFrameOfStateChangedTillNow() {
        return new TimeFrame(stateChangedTimeStamp, TimerUtils.getCurrentTimeStampAsRawLongFromSystem());
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

    public boolean isHealthy(){
        return currentState == State.Healthy;
    }

    public boolean isCarrying(){
        return currentState == State.Carrying;
    }

    public boolean isSick(){
        return currentState == State.Sick;
    }

}
