package com.orens.cshs.logic.state;

public class HealthyState extends AbstractHealthState{

    public HealthyState() {
        super(State.Healthy, SICK_TEMPERATURE_THRESHOLD-1);
    }

}
