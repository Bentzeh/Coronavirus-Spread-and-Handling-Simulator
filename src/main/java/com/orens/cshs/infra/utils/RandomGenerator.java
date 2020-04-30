package com.orens.cshs.infra.utils;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.models.Direction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * this class acts as a util class regarding a random generation of any kind
 */
public class RandomGenerator {


    private static final Random random = new Random();
    private static final int WIDTH = PropertiesFileReader.getBoardWidth();
    private static final int HEIGHT = PropertiesFileReader.getBoardHeight();


    private static final int PROBABILITY_LIST_SIZE = 5;

    private static final int[] probability40 = new int[]{1,1,0,0,0};
    private static final int[] probability60 = new int[]{1,1,1,0,0};
    private static final int[] probability80 = new int[]{1,1,1,1,0};

    /**
     * this method returns a random number x within a predefined limit, according to the given Width in the .properties file
     * @return a num ber between 0 to given Width
     */
    public static int getRandomXWithinBorders(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered RandomGenerator.getRandomXWithinBorders()");
        return (int)((Math.random() * WIDTH));
    }

    /**
     * this method returns a random number y within a predefined limit, according to the given Height in the .properties file
     * @return a num ber between 0 to given Height
     */
    public static int getRandomYWithinBorders(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered RandomGenerator.getRandomYWithinBorders()");
        return (int)((Math.random() * HEIGHT));
    }


    /**
     * this method return a random direction
     * @return a random direction (9 options including stay on the same spot)
     */
    public static Direction getRandomDirection(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered RandomGenerator.getRandomDirection()");
        List<Direction> directionList = Collections.unmodifiableList(Arrays.asList(Direction.values()));
        int amountOfDirections = directionList.size();
        return directionList.get(random.nextInt(amountOfDirections));
    }

    /**
     * generate a random number between 0 to num (non-inclusive)
     * @param num number that represents the top limit value of the number returned
     * @return a random number between 0 to num (non-inclusive)
     */
    public static int getRandomNumberInRange(int num) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered RandomGenerator.getRandomNumberInRange()");
        return (int)((Math.random() * num));
    }


    /**
     * this method raffle a true value with a 40% probability to be true
     * @return true with 40% probability
     */
    public static boolean trueWith40PercentProbability(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered RandomGenerator.trueWith40PercentProbability()");
        int index = RandomGenerator.getRandomNumberInRange(PROBABILITY_LIST_SIZE);
        return (probability40[index] == 1);
    }

    /**
     * this method raffle a true value with a 60% probability to be true
     * @return true with 60% probability
     */
    public static boolean trueWith60PercentProbability(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered RandomGenerator.trueWith60PercentProbability()");
        int index = RandomGenerator.getRandomNumberInRange(PROBABILITY_LIST_SIZE);
        return (probability60[index] == 1);
    }

    /**
     * this method raffle a true value with a 80% probability to be true
     * @return true with 80% probability
     */
    public static boolean trueWith80PercentProbability(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered RandomGenerator.trueWith80PercentProbability()");
        int index = RandomGenerator.getRandomNumberInRange(PROBABILITY_LIST_SIZE);
        return (probability80[index] == 1);
    }
}
