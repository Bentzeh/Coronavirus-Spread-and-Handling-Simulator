package com.orens.cshs.infra.utils;

import com.orens.cshs.pojos.Direction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomGenerator {

    private static final Random random = new Random();
    private static final int AMOUNT_OF_DIRECTIONS = 9;
    private static final int WIDTH = PropertiesFileReader.getBoardWidth();
    private static final int HEIGHT = PropertiesFileReader.getBoardHeight();


    public static int getRandomXWithinBorders(){
        return (int)((Math.random() * WIDTH));
    }

    public static int getRandomYWithinBorders(){
        return (int)((Math.random() * HEIGHT));
    }

    public static Direction getRandomDirection(){
        List<Direction> directionList = Collections.unmodifiableList(Arrays.asList(Direction.values()));
        int amountOfDirections = directionList.size();
        return directionList.get(random.nextInt(amountOfDirections));
    }


}
