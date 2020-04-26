package com.orens.cshs.models;

import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.strategy.AbstractLogicStrategy;
import com.orens.cshs.models.pojos.Location;

public class InspectorPerson extends Person{

    protected int amountOfPeopleMet;

    public InspectorPerson(Location location, AbstractHealthState currentHealthState, AbstractLogicStrategy logicStrategy) {
        super(location, currentHealthState, logicStrategy);

        this.amountOfPeopleMet = 0;
    }
}