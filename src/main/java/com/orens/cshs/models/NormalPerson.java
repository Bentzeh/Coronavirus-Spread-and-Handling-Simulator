package com.orens.cshs.models;


import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.strategy.AbstractLogicStrategy;

import java.awt.*;

/**
 * an implementation of the participant abstract class
 */
public class NormalPerson extends Person {

    /**
     * Constructor
     * @param logicStrategy a strategy object that holds the data and logic of this simulation
     */
    public NormalPerson(AbstractLogicStrategy logicStrategy) {
        super(logicStrategy);
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered NormalPerson.Constructor()");

        initColors();
        setColor(AbstractHealthState.State.Healthy);
    }

    /**
     * initialize the possible colors of this participant
     */
    private void initColors(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered NormalPerson.initColors()");

        participantColors.put(AbstractHealthState.State.Healthy, new Color(30, 144, 144));
        participantColors.put(AbstractHealthState.State.Carrying, new Color(220, 81, 10));
        participantColors.put(AbstractHealthState.State.Sick, new Color(203, 10, 10));
    }

    /**
     * executing a "tick" iteration by activating the logic in the strategy object
     */
    @Override
    public void iteration() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered NormalPerson.iteration()");
        logicStrategy.executeLogic(this);
    }

    /**
     * setting this participant color
     * @param state the health state that we want to set
     */
    @Override
    protected void setColor(AbstractHealthState.State state) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered NormalPerson.setColor()");
        Color c = participantColors.get(state);
        if (c == null){
            this.participantColor = Pixel.testColor;
        }else {
            this.participantColor = c;
        }
    }
}
