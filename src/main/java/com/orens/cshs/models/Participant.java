package com.orens.cshs.models;

import com.orens.cshs.infra.utils.TimerUtils;
import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.HealthState;
import com.orens.cshs.logic.strategy.AbstractLogicStrategy;
import com.orens.cshs.models.pojos.Location;
import com.orens.cshs.models.pojos.TimeFrame;

import java.util.Objects;

public abstract class Participant implements IInvocable {

    protected static long globalId = 0L;
    protected final long id;
    protected String name;

    protected Location location;
    //protected Map<HealthState.State, HealthState> healthStates;
    protected HealthState currentHealthState;
    protected AbstractLogicStrategy logicStrategy;

    protected long currentScheduledExecutionTime; //beginning of tick
    protected long locationChangeTimeStamp;


    public Participant(Location location, AbstractLogicStrategy logicStrategy) {
        this.id = ++globalId;
        this.name = "defaultName with id: "+id;

        this.location = new Location().getRandomLocation();
        this.setLocation(location);
        initHealthStates();
        this.logicStrategy = logicStrategy;

        this.currentScheduledExecutionTime = 0L;

    }

    private void initHealthStates(){
        //this.healthStates = new EnumMap<>(HealthState.State.class);
        this.currentHealthState = new HealthState();
        setCurrentHealthState(HealthState.State.Healthy);
    }

    @Override
    public void iteration() {
        logicStrategy.executeLogic(this);
    }

    @Override
    public void updateCurrentScheduledExecutionTime(long scheduledExecutionTime){
        setCurrentScheduledExecutionTime(scheduledExecutionTime);
    }

    public void setCurrentScheduledExecutionTime(long currentScheduledExecutionTime) {
        this.currentScheduledExecutionTime = currentScheduledExecutionTime;
    }

    // get Time Passed From Beginning Of Current Iteration
    public TimeFrame getTimeFrameOfCurrentScheduledExecutionTillNow() {
        return new TimeFrame(currentScheduledExecutionTime, TimerUtils.getCurrentTimeStampAsRawLongFromSystem());
    }

    // get Time Passed From Last Location Change
    public TimeFrame getTimeFrameOfLastLocationChangeTillNow(){
        return new TimeFrame(locationChangeTimeStamp, TimerUtils.getCurrentTimeStampAsRawLongFromSystem());
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

    public void setLocation(Location newLocation) {
        if (!location.equals(newLocation)){
            this.location = newLocation;
            this.locationChangeTimeStamp = TimerUtils.getCurrentTimeStampAsRawLongFromSystem();
        }
    }

    public HealthState getCurrentHealthState() {
        return currentHealthState;
    }

    public void setCurrentHealthState(HealthState.State currentHealthState) {
        //this.currentHealthState = healthStates.get(currentHealthState);

        this.currentHealthState.setStateWithTimeStamp(currentHealthState);
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
