package com.orens.cshs.models;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.logic.strategy.AbstractLogicStrategy;

public class InspectorPerson extends Person{

    protected static final long PEOPLE_MET_THRESHOLD = PropertiesFileReader.getInspectorSickThreshold();

    protected int amountOfPeopleMet;

    public InspectorPerson(AbstractLogicStrategy logicStrategy) {
        super(logicStrategy);

        this.amountOfPeopleMet = 0;
    }

    public boolean isMetToMuchPeople() {
        return amountOfPeopleMet > PEOPLE_MET_THRESHOLD;
    }

    public int getAmountOfPeopleMet() {
        return amountOfPeopleMet;
    }

    public void setAmountOfPeopleMet(int amountOfPeopleMet) {
        this.amountOfPeopleMet = amountOfPeopleMet;
    }

    @Override
    public void iteration() {
        logicStrategy.executeLogic(this);
    }
}