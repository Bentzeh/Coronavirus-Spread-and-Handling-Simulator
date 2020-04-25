package com.orens.cshs.control;

import com.orens.cshs.display.ConsoleDisplay;
import com.orens.cshs.display.IDisplay;
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
    private IDisplay display;
    private Participants participants;


    public void startPreparation(){
        initBoard();
        initDisplay();
        initParticipants();
    }

    private void initBoard() {
        int boardHeight = PropertiesFileReader.getBoardHeight();
        int boardLength = PropertiesFileReader.getBoardLength();
        this.board = new Board(boardHeight, boardLength);
    }

    private void initDisplay() {
        this.display = new ConsoleDisplay(this.board);
    }

    private void initParticipants() {
        this.participants = new Participants();

    }

    @Override public void run() { mainLoop(); }



    private void mainLoop(){
        LoggerHandler.getInstance().log(ReportLevel.INFO, "Current time is: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), ZoneId.systemDefault()));
        display.drawBoard();
        //participants.doIteration();
    }


}

