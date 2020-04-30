package com.orens.cshs.models;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.strategy.AbstractLogicStrategy;

import java.awt.*;

/**
 * an implementation of the participant abstract class
 */
public class InspectorPerson extends Person {

    protected static final long PEOPLE_MET_THRESHOLD = PropertiesFileReader.getInspectorSickThreshold();

    protected int amountOfPeopleMet;

    /**
     * Constructor
     * @param logicStrategy a strategy object that holds the data and logic of this simulation
     */
    public InspectorPerson(AbstractLogicStrategy logicStrategy) {
        super(logicStrategy);
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered InspectorPerson.Constructor()");
        this.amountOfPeopleMet = 0;
        initColors();
        setColor(AbstractHealthState.State.Healthy);
    }

    /**
     * initialize the possible colors of this participant
     */
    private void initColors(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered InspectorPerson.initColors()");

        participantColors.put(AbstractHealthState.State.Healthy, new Color(0, 102, 252));
        participantColors.put(AbstractHealthState.State.Carrying, new Color(172, 120, 16));
        participantColors.put(AbstractHealthState.State.Sick, new Color(87, 0, 0));
    }

    /**
     * checks if this object is exceeded the predefined amount of participants meetings
     * @return true if met more the predefined amount, false otherwise
     */
    public boolean isMetToMuchPeople() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered InspectorPerson.isMetToMuchPeople()");
        return amountOfPeopleMet > PEOPLE_MET_THRESHOLD;
    }

    /**
     * retrieves the amount of people that this object met
     * @return the amount of people that this object met
     */
    public int getAmountOfPeopleMet() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered InspectorPerson.getAmountOfPeopleMet()");
        return amountOfPeopleMet;
    }

    /**
     * set the amount of people that this object met
     * @param amountOfPeopleMet the amount of people that we want to set
     */
    public void setAmountOfPeopleMet(int amountOfPeopleMet) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered InspectorPerson.setAmountOfPeopleMet()");
        this.amountOfPeopleMet = amountOfPeopleMet;
    }


    /**
     * executing a "tick" iteration by activating the logic in the strategy object
     */
    @Override
    public void iteration() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered InspectorPerson.iteration()");
        logicStrategy.executeLogic(this);
    }


    /**
     * setting this participant color
     * @param state the health state that we want to set
     */
    @Override
    protected void setColor(AbstractHealthState.State state) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered InspectorPerson.setColor()");
        Color c = participantColors.get(state);
        if (c == null){
            this.participantColor = Pixel.testColor;
        }else {
            this.participantColor = c;
        }
    }
}