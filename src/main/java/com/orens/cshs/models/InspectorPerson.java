package com.orens.cshs.models;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.strategy.AbstractLogicStrategy;

import java.awt.*;

public class InspectorPerson extends Person{

    protected static final long PEOPLE_MET_THRESHOLD = PropertiesFileReader.getInspectorSickThreshold();

    protected int amountOfPeopleMet;

    public InspectorPerson(AbstractLogicStrategy logicStrategy) {
        super(logicStrategy);

        this.amountOfPeopleMet = 0;
        initColors();
        setColor(AbstractHealthState.State.Healthy);
    }

    private void initColors(){
        participantColors.put(AbstractHealthState.State.Healthy, new Color(0, 102, 252));
        participantColors.put(AbstractHealthState.State.Carrying, new Color(172, 120, 16));
        participantColors.put(AbstractHealthState.State.Sick, new Color(87, 0, 0));
    }

    public boolean isMetToMuchPeople() {
        return amountOfPeopleMet > PEOPLE_MET_THRESHOLD;
    }

    public int getAmountOfPeopleMet() {
        return amountOfPeopleMet;
    }

    public void setAmountOfPeopleMet(int amountOfPeopleMet) {
        this.amountOfPeopleMet = amountOfPeopleMet;
    }

    @Override
    public void iteration() {
        logicStrategy.executeLogic(this);
    }

    @Override
    protected void setColor(AbstractHealthState.State state) {
        Color c = participantColors.get(state);
        if (c == null){
            this.participantColor = Pixel.testColor;
        }else {
            this.participantColor = c;
        }
    }
}