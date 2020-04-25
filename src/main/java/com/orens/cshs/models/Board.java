package com.orens.cshs.models;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.pojos.Pixel;

public class Board {

    private final int fieldWidth;
    private final int fieldHeight;
    private Pixel[][] field;


    public Board() {
        this.fieldWidth = PropertiesFileReader.getBoardWidth();
        this.fieldHeight = PropertiesFileReader.getBoardHeight();
        initializeBoard();
    }

    private void initializeBoard() {
        this.field = new Pixel[fieldHeight][fieldWidth];

        for (int i = 0; i < field.length; ++i) {
            field[i] = new Pixel[fieldWidth];
            for (int j = 0; j < field[0].length; ++j) {
                field[i][j] = new Pixel(j, i);
            }
        }
    }

    public void setValueAt(int x, int y, String newValue){
        field[y][x].setValue(newValue);
    }


    public Pixel[][] getField() {
        return field;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }
}
