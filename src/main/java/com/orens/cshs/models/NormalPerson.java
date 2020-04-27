package com.orens.cshs.models;


import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.strategy.AbstractLogicStrategy;

import java.awt.*;

public class NormalPerson extends Person {

    public NormalPerson(AbstractLogicStrategy logicStrategy) {
        super(logicStrategy);


        initColors();
        setColor(AbstractHealthState.State.Healthy);
    }

    private void initColors(){
        participantColors.put(AbstractHealthState.State.Healthy, new Color(30, 144, 144));
        participantColors.put(AbstractHealthState.State.Carrying, new Color(220, 81, 10));
        participantColors.put(AbstractHealthState.State.Sick, new Color(203, 10, 10));
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
