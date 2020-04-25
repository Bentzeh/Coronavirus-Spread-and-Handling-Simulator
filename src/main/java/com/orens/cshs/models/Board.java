package com.orens.cshs.models;

import com.orens.cshs.pojos.Pixel;

public class Board {

    private final Object lock = new Object();

    private int height;
    private int length;
    private Pixel[][] field;


    public Board(int height, int length) {
        this.height = height;
        this.length = length;
        initializeBoard();

    }

    private void initializeBoard() {
        this.field = new Pixel[height][length];

        for (int i = 0; i < field.length; ++i) {
            field[i] = new Pixel[length];
            for (int j = 0; j < field[0].length; ++j) {
                field[i][j] = new Pixel(j, i);
            }
        }
    }

    public void setValueAt(int x, int y, String newValue){
        field[y][x].setValue(newValue);
    }


    public Pixel[][] getField() {
        synchronized (lock){
            return field;
        }
    }
}
