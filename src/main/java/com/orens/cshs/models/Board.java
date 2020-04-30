package com.orens.cshs.models;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.PropertiesFileReader;

/**
 * this class represent the board on which the simulation occur
 */
public class Board {

    private static final int FIELD_WIDTH  = PropertiesFileReader.getBoardWidth();
    private static final int FIELD_HEIGHT = PropertiesFileReader.getBoardHeight();
    private Pixel[][] field;

    /**
     * Constructor
     */
    public Board() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Board.Constructor()");
        initializeBoard();
    }


    /**
     * initialize the board internal objects
     */
    private void initializeBoard() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Board.initializeBoard()");
        this.field = new Pixel[FIELD_HEIGHT][FIELD_WIDTH];

        for (int i = 0; i < field.length; ++i) {
            field[i] = new Pixel[FIELD_WIDTH];
            for (int j = 0; j < field[0].length; ++j) {
                field[i][j] = new Pixel(j, i);
            }
        }
    }

    /**
     * gets the board internal object
     * @return a matrix of Pixel object
     */
    public Pixel[][] getField() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Board.getField()");
        return field;
    }

    /**
     *  gets the board internal object width
     * @return the board width
     */
    public int getFieldWidth() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Board.getFieldWidth()");
        return FIELD_WIDTH;
    }

    /**
     *  gets the board internal object height
     * @return the board height
     */
    public int getFieldHeight() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Board.getFieldHeight()");
        return FIELD_HEIGHT;
    }

    /**
     *  added a data object to the board
     * @param participant the data element
     * @return true if succeeded to add else false
     */
    public boolean addParticipantToLocation(Participant participant) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Board.addParticipantToLocation()");
        Location participantLocation = participant.getLocation();
        Pixel pixel = field[participantLocation.getY()][participantLocation.getX()];
        return pixel.addParticipantToPixel(participant);
    }

    /**
     *  remove a data object from the board
     * @param participant the data element
     * @return true if succeeded to remove else false
     */
    public boolean removeParticipantFromLocation(Participant participant) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Board.removeParticipantFromLocation()");
        Location participantLocation = participant.getLocation();
        Pixel pixel = field[participantLocation.getY()][participantLocation.getX()];
        return pixel.removeParticipantFromPixel(participant);
    }
}
