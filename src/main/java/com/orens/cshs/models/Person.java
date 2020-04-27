package com.orens.cshs.models;


import com.orens.cshs.logic.strategy.AbstractLogicStrategy;
import com.orens.cshs.models.pojos.Location;

public abstract class Person extends Participant {

    public Person(Location location, AbstractLogicStrategy logicStrategy) {
        super(location, logicStrategy);
    }
}
