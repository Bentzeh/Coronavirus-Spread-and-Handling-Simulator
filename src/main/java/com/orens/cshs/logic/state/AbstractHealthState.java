package com.orens.cshs.logic.state;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.TimerUtils;
import com.orens.cshs.models.InspectorPerson;
import com.orens.cshs.models.Participant;
import com.orens.cshs.models.pojos.TimeFrame;

public abstract class AbstractHealthState {

    public enum State {Healthy, Carrying, Sick, Dead;}
    protected State currentState;


    protected static final int  INSPECTOR_SICK_THRESHOLD   = PropertiesFileReader.getInspectorSickThreshold();;
    protected static final int  SICK_TEMPERATURE_THRESHOLD = PropertiesFileReader.getSickTemperatureThreshold();
    protected static final int  CONTAGIOUS_RADIUS          = PropertiesFileReader.getContagiousRadius();
    protected static final long ISOLATION_TIME             = PropertiesFileReader.getIsolationTimeInMilliseconds();

    protected long timeInIsolation;
    protected long stateChangedTimeStamp;

    protected int bodyHeat;
    protected boolean isIsolated;


    public AbstractHealthState(long stateChangedTimeStamp, int bodyHeat, State state) {
        this.stateChangedTimeStamp = stateChangedTimeStamp;
        this.bodyHeat = bodyHeat;

        this.timeInIsolation = -1L;
        this.isIsolated = false;
        this.currentState = state;
    }

    public TimeFrame getTimeFrameOfStateChangedTillNow() {
        return new TimeFrame(stateChangedTimeStamp, TimerUtils.getCurrentTimeStampAsRawLongFromSystem());
    }

    abstract public boolean changeState(Participant participant, Participant otherParticipant);
    abstract public boolean changeState(InspectorPerson inspectorPerson, Participant otherParticipant);


    public boolean isIsolated(){
        return isIsolated;
    }

    public void setIsolation(){
        timeInIsolation = TimerUtils.getCurrentTimeStampAsRawLongFromSystem();
        isIsolated = true;
    }

    public boolean updateAndReportIsolationTime(){
        if (TimerUtils.getMillisTimeGapInSecondsFromNow(timeInIsolation) >= ISOLATION_TIME){
            isIsolated = false;
            return false;
        }
        return true;
    }

    public boolean isHealthy(){
        return currentState == State.Healthy;
    }

}
