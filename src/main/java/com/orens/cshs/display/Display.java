package com.orens.cshs.display;

import com.orens.cshs.models.ParticipantsData;

public class Display {

    private AbstractOutput outputDisplay;

    public Display(ParticipantsData participantsData) {
        outputDisplay = new GridLayoutOutput(participantsData.getBoard());
        outputDisplay.InitializeDisplay();
    }

    public AbstractOutput getOutputDisplay() {
        return outputDisplay;
    }

    public void setOutputDisplay(AbstractOutput outputDisplay) {
        this.outputDisplay = outputDisplay;
    }

    public void updateDisplayView() {
        outputDisplay.updateDisplayView();
    }
}
