package com.orens.cshs.models;

import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.strategy.AbstractLogicStrategy;
import com.orens.cshs.models.pojos.Location;

import java.util.Objects;

public abstract class Participant implements IInvocable {

    protected static long globalId = 0L;
    protected final long id;
    protected String name;

    protected Location location;
    protected AbstractHealthState currentHealthState;
    protected AbstractLogicStrategy logicStrategy;

    protected int bodyHeat; //  isSick <- if high than 38 ? true : false ;
    protected boolean isCarry;
    protected boolean isDead;
    protected boolean isInspector;

    protected long timePassedFromLastLocationChange;

    public Participant(Location location, AbstractHealthState currentHealthState, AbstractLogicStrategy logicStrategy) {
        this.id = ++globalId;
        this.name = "defaultName with id: "+id;

        this.location = location;
        this.currentHealthState = currentHealthState;
        this.logicStrategy = logicStrategy;

        // TODO: 26/04/2020 initialize bodyHeat and such

    }

    @Override
    public void iteration() {
        logicStrategy.executeLogic(this);
    }

    @Override
    public void updateTimePassedFromLastLocationChange(){
        // TODO: 26/04/2020  update passing time from last change
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public AbstractHealthState getCurrentHealthState() {
        return currentHealthState;
    }

    public void setCurrentHealthState(AbstractHealthState currentHealthState) {
        this.currentHealthState = currentHealthState;
    }

    public AbstractLogicStrategy getLogicStrategy() {
        return logicStrategy;
    }

    public void setLogicStrategy(AbstractLogicStrategy logicStrategy) {
        this.logicStrategy = logicStrategy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
