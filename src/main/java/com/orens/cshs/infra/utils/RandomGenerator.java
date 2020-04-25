package com.orens.cshs.infra.utils;

import java.util.Random;

public class RandomGenerator {

    private static final Random random = new Random();
    private static final int height = PropertiesFileReader.getBoardHeight()-1;
    private static final int length = PropertiesFileReader.getBoardLength()-1;

    public static int getRandomWithinBorders(){
        return (int)((Math.random() * (Math.min(height, length))));
    }


}
