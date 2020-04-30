package com.orens.cshs.logic.state;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;

/**
 * an implementation of the abstract health state
 * this state represent a carrying of the disease state
 */
public class CarryingState extends AbstractHealthState {

    /**
     * constructor
     */
    public CarryingState() {
        super(State.Carrying, SICK_TEMPERATURE_THRESHOLD-1);
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered CarryingState.Constructor()");
    }

}
