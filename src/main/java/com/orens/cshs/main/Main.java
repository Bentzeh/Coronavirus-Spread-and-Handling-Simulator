package com.orens.cshs.main;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.PropertiesFileReader;

public class Main {

    public static void main(String[] args) {

        // if all start healthy no one will be sick


        // read from properties file
        // read the strategy
        // contagiousRadius stepDistance tickTime isolationTime amountOfHealthyPeople amountOfCarryingPeople amountOfSickPeople amountOfInspectors

        LoggerHandler.getInstance().log(ReportLevel.INFO, PropertiesFileReader.getMyName());
        //------
        CSHS.startSimulation();
    }

}
