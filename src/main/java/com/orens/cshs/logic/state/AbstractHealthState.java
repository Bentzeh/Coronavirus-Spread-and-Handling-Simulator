package com.orens.cshs.logic.state;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.TimerUtils;
import com.orens.cshs.models.TimeFrame;

/**
 * this abstract class represent the state of a participant
 */
public abstract class AbstractHealthState {

    protected static final int  SICK_TEMPERATURE_THRESHOLD = PropertiesFileReader.getSickTemperatureThreshold();

    public enum State {Healthy, Carrying, Sick;}
    protected State currentState;

    protected long stateChangedTimeStamp;
    protected int bodyHeat;

    /**
     * constructor
     * @param state the health state enum of the participant
     * @param bodyHeat the participant body heat
     */
    public AbstractHealthState(State state, int bodyHeat) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractHealthState.Constructor()");
        this.currentState = state;
        this.bodyHeat = bodyHeat;

        this.stateChangedTimeStamp = -1L;
    }

    /**
     * takes a timestamp (in use each time that a state is changed)
     */
    public void takeStateChangedTimestamp() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractHealthState.takeStateChangedTimestamp()");
        this.stateChangedTimeStamp = TimerUtils.getCurrentTimeStampAsRawLongFromSystem();
    }

    /**
     * retrieves the time frame from the time that this participant moved to this state till now
     * @return the time frame from the time that this participant moved to this state till now
     */
    public TimeFrame getTimeFrameOfStateChangedTillNow() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractHealthState.getTimeFrameOfStateChangedTillNow()");
        return new TimeFrame(stateChangedTimeStamp, TimerUtils.getCurrentTimeStampAsRawLongFromSystem());
    }

    /**
     * retrieves this state body heat
     * @return the body heat
     */
    public int getBodyHeat() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractHealthState.getBodyHeat()");
        return bodyHeat;
    }

    /**
     * checks is this participant is healthy
     * @return true if is healthy. false otherwise
     */
    public boolean isHealthy(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractHealthState.isHealthy()");
        return currentState == State.Healthy;
    }

    /**
     * checks is this participant is carrying
     * @return true if is carrying. false otherwise
     */
    public boolean isCarrying(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractHealthState.isCarrying()");
        return currentState == State.Carrying || currentState == State.Sick;
    }

    /**
     * checks is this participant is sick
     * @return true if is sick. false otherwise
     */
    public boolean isSick(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractHealthState.isSick()");
        return currentState == State.Sick;
    }


}
