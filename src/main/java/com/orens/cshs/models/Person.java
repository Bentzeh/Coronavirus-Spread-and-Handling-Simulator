package com.orens.cshs.models;


import com.orens.cshs.logic.strategy.AbstractLogicStrategy;

public abstract class Person extends Participant {

    public Person(AbstractLogicStrategy logicStrategy) {
        super(logicStrategy);
    }
}
