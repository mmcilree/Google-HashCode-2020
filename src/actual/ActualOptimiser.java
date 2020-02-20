package actual;

import common.Optimiser;
import common.TabularData;

import java.util.ArrayList;

public class ActualOptimiser implements Optimiser {

    public class Library {
        int numBooks;
        int numDays;
        int numShip;
        ArrayList<Integer> bookIDs;
    }

    int numDifferentBooks;
    int numLibraries;
    int numDays;
    ArrayList<Integer> bookVals;
    ArrayList<Library> libraries = new ArrayList<>();

    @Override
    public TabularData optimise(TabularData inputData) {
        init(inputData);
        inputData.clear();
        return new TabularData();
    }

    public void init(TabularData inputData) {

        numDifferentBooks = inputData.getElementAsInt(0, 0);
        numLibraries = inputData.getElementAsInt(0, 1);
        numDays = inputData.getElementAsInt(0, 2);
        bookVals = inputData.getRowAsIntList(1);
        for(int i = 2; i < (numLibraries*2) + 2; i+=2) {
            Library l = new Library();

            l.numBooks =  inputData.getElementAsInt(i,0);
            l.numDays = inputData.getElementAsInt(i, 1);
            l.numShip = inputData.getElementAsInt(i, 2);
            l.bookIDs = inputData.getRowAsIntList(i+1);
            libraries.add(l);
        }

        System.out.println(numDifferentBooks + " " + numLibraries + " " + numDays);
        System.out.println();

    }
}
