package com.orens.cshs.infra.utils;

import java.util.Random;

public class RandomGenerator {

    private static final Random random = new Random();
    private static final int width = PropertiesFileReader.getBoardWidth();
    private static final int height = PropertiesFileReader.getBoardHeight();

    public static int getRandomWithinBorders(){
        return (int)((Math.random() * (Math.min(width, height))));
    }


}
