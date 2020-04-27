package com.orens.cshs.models;

import com.orens.cshs.infra.utils.TimerUtils;
import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.state.CarryingState;
import com.orens.cshs.logic.state.HealthyState;
import com.orens.cshs.logic.state.SickState;
import com.orens.cshs.logic.strategy.AbstractLogicStrategy;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public abstract class Participant implements IInvocable {

    protected static long globalId = 0L;
    protected final long id;
    protected String name;

    protected Location location;
    protected Map<AbstractHealthState.State, AbstractHealthState> healthStates;
    protected AbstractHealthState currentHealthState;
    protected AbstractLogicStrategy logicStrategy;

    protected long currentScheduledExecutionTime; //beginning of tick
    protected long locationChangeTimeStamp;


    public Participant(AbstractLogicStrategy logicStrategy) {
        this.id = ++globalId;
        this.name = "defaultName with id: "+id;

        setLocation(new Location().getRandomLocation());
        initHealthStates();
        this.logicStrategy = logicStrategy;

        this.currentScheduledExecutionTime = 0L;

    }

    private void initHealthStates(){
        this.healthStates = new EnumMap<>(AbstractHealthState.State.class);
        healthStates.put(AbstractHealthState.State.Healthy,  new HealthyState());
        healthStates.put(AbstractHealthState.State.Carrying, new CarryingState());
        healthStates.put(AbstractHealthState.State.Sick,     new SickState());

        setCurrentHealthState(AbstractHealthState.State.Healthy);
    }

    public AbstractHealthState getCurrentHealthState() {
        return currentHealthState;
    }

    public void setCurrentHealthState(AbstractHealthState.State HealthState) {
        this.currentHealthState = healthStates.get(HealthState);
        this.currentHealthState.takeStateChangedTimestamp();
    }




    @Override
    public void updateCurrentScheduledExecutionTime(long scheduledExecutionTime){
        this.currentScheduledExecutionTime = scheduledExecutionTime;
    }

    // get Time Passed From Beginning Of Current Iteration
    public TimeFrame getTimeFrameOfCurrentScheduledExecutionTillNow() {
        return new TimeFrame(currentScheduledExecutionTime, TimerUtils.getCurrentTimeStampAsRawLongFromSystem());
    }

    // get Time Passed From Last Location Change
    public TimeFrame getTimeFrameOfLastLocationChangeTillNow(){
        return new TimeFrame(locationChangeTimeStamp, TimerUtils.getCurrentTimeStampAsRawLongFromSystem());
    }

    public void setLocation(Location newLocation) {
        if (!location.equals(newLocation)){
            this.location = newLocation;
            this.locationChangeTimeStamp = TimerUtils.getCurrentTimeStampAsRawLongFromSystem();
        }
    }

    public Location getLocation() {
        return location;
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
