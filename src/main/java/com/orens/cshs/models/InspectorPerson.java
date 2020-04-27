package com.orens.cshs.models;

import com.orens.cshs.logic.strategy.AbstractLogicStrategy;
import com.orens.cshs.models.pojos.Location;

public class InspectorPerson extends Person{

    protected int amountOfPeopleMet;

    public InspectorPerson(Location location, AbstractLogicStrategy logicStrategy) {
        super(location, logicStrategy);

        this.amountOfPeopleMet = 0;
    }

    public int getAmountOfPeopleMet() {
        return amountOfPeopleMet;
    }

    public void setAmountOfPeopleMet(int amountOfPeopleMet) {
        this.amountOfPeopleMet = amountOfPeopleMet;
    }
}