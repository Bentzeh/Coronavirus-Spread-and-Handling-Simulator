package com.orens.cshs.display;

import com.orens.cshs.models.Board;

public abstract class AbstractOutput {

    protected Board board;


    public AbstractOutput(Board board) {
        this.board = board;
    }

    abstract public void InitializeDisplay();
    abstract public void updateDisplayView();

}
