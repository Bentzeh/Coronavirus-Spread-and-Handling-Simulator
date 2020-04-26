package com.orens.cshs.logic.state;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.TimerUtils;
import com.orens.cshs.models.InspectorPerson;
import com.orens.cshs.models.Participant;
import com.orens.cshs.models.pojos.TimeFrame;

public abstract class AbstractHealthState {

    protected static final int INSPECTOR_SICK_THRESHOLD = 3;
    protected static final int SICK_TEMPERATURE_THRESHOLD = PropertiesFileReader.getSickTemperatureThreshold();
    protected static final int  CONTAGIOUS_RADIUS = PropertiesFileReader.getContagiousRadius();
    protected static final long ISOLATION_TIME    = PropertiesFileReader.getIsolationTimeInMilliseconds();

    protected long stateChangedTimeStamp;
    protected int bodyHeat;

    public AbstractHealthState(long stateChangedTimeStamp, int bodyHeat) {
        this.stateChangedTimeStamp = stateChangedTimeStamp;
        this.bodyHeat = bodyHeat;
    }

    public TimeFrame getTimeFrameOfStateChangedTillNow() {
        return new TimeFrame(stateChangedTimeStamp, TimerUtils.getCurrentTimeStampAsRawLongFromSystem());
        //return TimerUtils.getMillisTimeGapInSecondsFromNow(stateChangedTimeStamp);
    }

    abstract public boolean changeState(Participant participant, Participant otherParticipant);
    abstract public boolean changeState(InspectorPerson inspectorPerson, Participant otherParticipant);


}
