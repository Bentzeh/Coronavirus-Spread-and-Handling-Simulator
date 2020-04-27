package com.orens.cshs.logic.state;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.TimerUtils;
import com.orens.cshs.models.TimeFrame;

public abstract class AbstractHealthState {

    protected static final int  SICK_TEMPERATURE_THRESHOLD = PropertiesFileReader.getSickTemperatureThreshold();

    public enum State {Healthy, Carrying, Sick;}
    protected State currentState;

    protected long stateChangedTimeStamp;
    protected int bodyHeat;

    public AbstractHealthState(State state, int bodyHeat) {

        this.currentState = state;
        this.bodyHeat = bodyHeat;

        this.stateChangedTimeStamp = -1L;
    }

    public void takeStateChangedTimestamp() {
        this.stateChangedTimeStamp = TimerUtils.getCurrentTimeStampAsRawLongFromSystem();
    }

    public TimeFrame getTimeFrameOfStateChangedTillNow() {
        return new TimeFrame(stateChangedTimeStamp, TimerUtils.getCurrentTimeStampAsRawLongFromSystem());
    }

    public int getBodyHeat() {
        return bodyHeat;
    }

    public boolean isHealthy(){
        return currentState == State.Healthy;
    }

    public boolean isCarrying(){
        return currentState == State.Carrying || currentState == State.Sick;
    }

    public boolean isSick(){
        return currentState == State.Sick;
    }


}
