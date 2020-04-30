package com.orens.cshs.logic.state;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.TimerUtils;

/**
 * an implementation of the abstract health state
 * this state represent a sick state
 */
public class SickState extends AbstractHealthState{

    protected static final long ISOLATION_TIME = PropertiesFileReader.getIsolationTimeInMilliseconds();

    protected long startIsolationTimestamp;
    protected boolean isIsolated;

    /**
     * constructor
     */
    public SickState() {
        super(State.Sick, SICK_TEMPERATURE_THRESHOLD+1);
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SickState.Constructor()");

        this.startIsolationTimestamp = -1L;
        this.isIsolated = false;
    }

    /**
     * set this state as Isolated
     */
    public void setIsolation(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SickState.setIsolation()");
        startIsolationTimestamp = TimerUtils.getCurrentTimeStampAsRawLongFromSystem();
        isIsolated = true;
    }

    /**
     * check if Isolation time is over
     * @return true if it is over, false otherwise
     */
    public boolean isIsolationTimeOver(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SickState.isIsolationTimeOver()");
        long isolationTime = TimerUtils.getMillisTimeGapInMillisecondsFromNow(startIsolationTimestamp);
        if (isolationTime >= ISOLATION_TIME){
            isIsolated = false;
            return true;
        }
        return false;
    }

    /**
     * checks is this state is in isolation mode
     * @return true if isolated, false otherwise
     */
    public boolean isIsolated(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SickState.isIsolated()");
        return isIsolated;
    }

}