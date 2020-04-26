package com.orens.cshs.models;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.models.pojos.Location;
import com.orens.cshs.models.pojos.Pixel;

public class Board {

    private static final int FIELD_WIDTH  = PropertiesFileReader.getBoardWidth();
    private static final int FIELD_HEIGHT = PropertiesFileReader.getBoardHeight();
    private Pixel[][] field;

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        this.field = new Pixel[FIELD_HEIGHT][FIELD_WIDTH];

        for (int i = 0; i < field.length; ++i) {
            field[i] = new Pixel[FIELD_WIDTH];
            for (int j = 0; j < field[0].length; ++j) {
                field[i][j] = new Pixel(j, i);
            }
        }
    }

    public Pixel[][] getField() {
        return field;
    }

    public int getFieldWidth() {
        return FIELD_WIDTH;
    }

    public int getFieldHeight() {
        return FIELD_HEIGHT;
    }

    public boolean addParticipantToLocation(Participant participant) {
        Location participantLocation = participant.getLocation();
        Pixel pixel = field[participantLocation.getY()][participantLocation.getX()];
        return pixel.addParticipantToPixel(participant);
    }

    public boolean removeParticipantFromLocation(Participant participant) {
        Location participantLocation = participant.getLocation();
        Pixel pixel = field[participantLocation.getY()][participantLocation.getX()];
        return pixel.removeParticipantFromPixel(participant);
    }
}
