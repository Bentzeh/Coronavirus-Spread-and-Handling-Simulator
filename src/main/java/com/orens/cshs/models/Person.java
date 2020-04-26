package com.orens.cshs.models;


import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.strategy.AbstractLogicStrategy;
import com.orens.cshs.models.pojos.Location;

public abstract class Person extends Participant {

    public Person(Location location, AbstractHealthState currentHealthState, AbstractLogicStrategy logicStrategy) {
        super(location, currentHealthState, logicStrategy);
    }
}
