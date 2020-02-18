package practice;

import common.Optimiser;
import common.TabularData;

import java.util.ArrayList;
import java.util.Collections;

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
        lessDumbSolution();
        return output;
    }

    private void init(TabularData inputData) {
        output.clear();
        maxSlices = inputData.getElementAsInt(0, 0);
        pizzaKinds = inputData.getElementAsInt(0, 1);
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

    //Idea: take the sum of all the numbers and then subtract the min each time until we hit the max
    private void lessDumbSolution() {
        int totalslices = 0;
        ArrayList<Integer> pizzaIDs = new ArrayList<>();
        for(int i = 0; i < sliceNumbers.size(); i++) {
            pizzaIDs.add(i);
        }
        ArrayList<Integer> sliceNumberCopy = (ArrayList<Integer>)sliceNumbers.clone();
        for(int sliceNo : sliceNumbers) {
            totalslices += sliceNo;
        }

        while(totalslices >= maxSlices) {
            //System.out.println(totalslices);
            int min = Collections.min(sliceNumberCopy);
            totalslices -= min;
            //System.out.println(totalslices + " vs " + maxSlices);
            pizzaIDs.remove(Integer.valueOf(sliceNumbers.lastIndexOf(min)));
            sliceNumberCopy.remove(Integer.valueOf(min));
        }

        ArrayList<String> line1 = new ArrayList<>();
        line1.add("" + pizzaIDs.size());
        output.addRow(line1);


        output.addRowOfInts(pizzaIDs);

        int finalTotal = 0;

        for(int sliceNo : sliceNumberCopy) {
            finalTotal += sliceNo;
        }

        System.out.println(finalTotal);
    }


}
