package com.orens.cshs.models;


import com.orens.cshs.logic.strategy.AbstractLogicStrategy;

public class NormalPerson extends Person{

    public NormalPerson(AbstractLogicStrategy logicStrategy) {
        super(logicStrategy);
    }

    @Override
    public void iteration() {
        logicStrategy.executeLogic(this);
    }

}
