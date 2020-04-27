package com.orens.cshs.models;


import com.orens.cshs.logic.strategy.AbstractLogicStrategy;
import com.orens.cshs.models.pojos.Location;

public class NormalPerson extends Person{

    public NormalPerson(Location location, AbstractLogicStrategy logicStrategy) {
        super(location, logicStrategy);
    }

}
