package com.orens.cshs.display;

import com.orens.cshs.models.Board;

public abstract class AbstractDisplay {

    protected Board board;


    public AbstractDisplay(Board board) {
        this.board = board;
    }

    abstract public void InitializeDisplay();
    abstract public void updateDisplayView();

}
