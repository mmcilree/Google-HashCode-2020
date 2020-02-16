package practice;

import common.Optimiser;
import common.TabularData;

/**
 * This is an example of how we might override the optimiser for the 2020 practice problem.
 */
public class PracticeOptimiser implements Optimiser {
    @Override
    public TabularData optimise(TabularData inputData) {
        //Where the work happens

        //Currently do nothing
        return inputData;
    }
}
