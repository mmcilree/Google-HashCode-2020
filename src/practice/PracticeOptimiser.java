package practice;

import common.Optimiser;
import common.TabularData;

import java.util.ArrayList;

/**
 * This is an example of how we might override the optimiser for the 2020 practice problem.
 */
public class PracticeOptimiser implements Optimiser {
    private int maxSlices;
    private int pizzaKinds;
    private ArrayList<Integer> sliceNumbers;
    TabularData output = new TabularData();

    @Override
    public TabularData optimise(TabularData inputData) {
        init(inputData);
        dumbSolution();
        return output;
    }

    private void init(TabularData inputData) {
        output.clear();
        maxSlices = inputData.getElementAsInt(0, 0);
        pizzaKinds = inputData.getElementAsInt(0, 0);
        sliceNumbers = inputData.getRowAsIntList(1);

    }

    private void dumbSolution() {
        int slicesLeft = maxSlices;
        int kindCount = 0;
        ArrayList<String> kindList = new ArrayList<>();

        for(int i : sliceNumbers) {
            //System.out.println(slicesLeft);
            if(slicesLeft - i < 0) {
                continue;
            } else {
                slicesLeft -= i;
            }
            kindList.add("" + kindCount);
            kindCount++;
        }

        ArrayList<String> line1 = new ArrayList<>();
        line1.add("" + kindCount);

        output.addRow(line1);
        output.addRow(kindList);
    }


}
