package com.orens.cshs.control;

import com.orens.cshs.display.AbstractDisplay;
import com.orens.cshs.display.GridLayoutDisplay;
import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.models.Board;
import com.orens.cshs.models.Participants;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;

public class SimulationController  extends TimerTask {
    private Board board;
    private AbstractDisplay display;
    private Participants participants;


    public void startPreparation(){
        initBoard();
        initDisplay();
        initParticipants();
    }

    private void initBoard() {
        int boardHeight = PropertiesFileReader.getBoardHeight();
        int boardWidth = PropertiesFileReader.getBoardWidth();
        board = new Board(boardWidth, boardHeight);
    }

    private void initDisplay() {
        //display = new ConsoleDisplay(board);
        display = new GridLayoutDisplay(board);
        display.InitializeDisplay();
    }

    private void initParticipants() {
        participants = new Participants(board);
    }

    @Override public void run() { mainLoop(); }



    private void mainLoop(){
        LoggerHandler.getInstance().log(ReportLevel.INFO, "Current time is: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), ZoneId.systemDefault()));
        display.updateDisplayView();
        participants.doIteration();
    }


}

