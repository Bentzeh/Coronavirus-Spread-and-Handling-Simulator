package com.orens.cshs.logic.state;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;

/**
 * an implementation of the abstract health state
 * this state represent a healthy state
 */
public class HealthyState extends AbstractHealthState{

    /**
     * constructor
     */
    public HealthyState() {
        super(State.Healthy, SICK_TEMPERATURE_THRESHOLD-1);
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered HealthyState.Constructor()");
    }

}
