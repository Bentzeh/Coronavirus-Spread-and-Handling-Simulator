package com.orens.cshs.logic.state;


public class CarryingState extends AbstractHealthState {

    public CarryingState() {
        super(State.Carrying, SICK_TEMPERATURE_THRESHOLD-1);
    }

}
