package com.orens.cshs.control;

import com.orens.cshs.display.AbstractDisplay;
import com.orens.cshs.display.GridLayoutDisplay;
import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.models.Board;
import com.orens.cshs.models.ParticipantsData;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;

public class SimulationController  extends TimerTask {
    private Board board;
    private AbstractDisplay display;
    private ParticipantsData participantsData;


    public void startPreparation(){
        initBoard();
        initDisplay();
        initParticipants();
    }

    private void initBoard() {
        board = new Board();
    }

    private void initDisplay() {
        //display = new ConsoleDisplay(board);
        display = new GridLayoutDisplay(board);
        display.InitializeDisplay();
    }

    private void initParticipants() {
        participantsData = new ParticipantsData(board);
    }

    @Override public void run() { mainLoop(); }



    private void mainLoop(){
        LoggerHandler.getInstance().log(ReportLevel.INFO, "Current time is: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), ZoneId.systemDefault()));
        display.updateDisplayView();
        participantsData.doIteration();
    }


}

