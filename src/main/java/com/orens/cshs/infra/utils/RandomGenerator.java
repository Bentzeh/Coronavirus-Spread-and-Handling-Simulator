package com.orens.cshs.infra.utils;

import com.orens.cshs.models.Direction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomGenerator {


    private static final Random random = new Random();
    private static final int WIDTH = PropertiesFileReader.getBoardWidth();
    private static final int HEIGHT = PropertiesFileReader.getBoardHeight();


    private static final int PROBABILITY_LIST_SIZE = 5;

    private static final int[] probability40 = new int[]{1,1,0,0,0};
    private static final int[] probability60 = new int[]{1,1,1,0,0};
    private static final int[] probability80 = new int[]{1,1,1,1,0};


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


    public static int getRandomNumberInRange(int num) {
        return (int)((Math.random() * num));
    }



    public static boolean trueWith40PercentProbability(){
        int index = RandomGenerator.getRandomNumberInRange(PROBABILITY_LIST_SIZE);
        return (probability40[index] == 1);
    }

    public static boolean trueWith60PercentProbability(){
        int index = RandomGenerator.getRandomNumberInRange(PROBABILITY_LIST_SIZE);
        return (probability60[index] == 1);
    }

    public static boolean trueWith80PercentProbability(){
        int index = RandomGenerator.getRandomNumberInRange(PROBABILITY_LIST_SIZE);
        return (probability80[index] == 1);
    }
}
