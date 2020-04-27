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

}