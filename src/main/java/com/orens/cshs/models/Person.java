package com.orens.cshs.models;


import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.logic.strategy.AbstractLogicStrategy;

/**
 * this class represent a sub tree in the Participant simulation hierarchy mainly for separation for future expanding options
 */
public abstract class Person extends Participant {

    /**
     * constructor
     * @param logicStrategy a strategy object that holds the data and logic of this simulation
     */
    public Person(AbstractLogicStrategy logicStrategy) {
        super(logicStrategy);
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Person.Constructor()");
    }
}
