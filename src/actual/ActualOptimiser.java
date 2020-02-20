package actual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import common.Optimiser;
import common.TabularData;

public class ActualOptimiser implements Optimiser {

    public class Library implements Comparable {
        int numBooks;
        int numDays;
        int numShip;
        int totalVal;
        ArrayList<Integer> bookIDs;

        public void calculateTotalVal() {
            totalVal = 0;
            for (int i : bookIDs) {
                totalVal += bookVals.get(i);
            }
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Library) {
                return Integer.compare(((Library) o).totalVal, this.totalVal);
            }
            return 0;
        }
    }

    int numDifferentBooks;
    int numLibraries;
    int maxDays;
    ArrayList<Integer> bookVals;
    ArrayList<Library> libraries = new ArrayList<>();

    @Override
    public TabularData optimise(TabularData inputData) {
        init(inputData);
        inputData.clear();
        algorithm1();
        return new TabularData();
    }

    public void init(TabularData inputData) {

        numDifferentBooks = inputData.getElementAsInt(0, 0);
        numLibraries = inputData.getElementAsInt(0, 1);
        maxDays = inputData.getElementAsInt(0, 2);
        bookVals = inputData.getRowAsIntList(1);
        for (int i = 2; i < (numLibraries * 2) + 2; i += 2) {
            Library l = new Library();

            l.numBooks = inputData.getElementAsInt(i, 0);
            l.numDays = inputData.getElementAsInt(i, 1);
            l.numShip = inputData.getElementAsInt(i, 2);
            l.bookIDs = inputData.getRowAsIntList(i + 1);
            l.calculateTotalVal();
            libraries.add(l);
        }

        System.out.println(numDifferentBooks + " " + numLibraries + " " + maxDays);
        System.out.println();

    }

    public void algorithm1() {
        Collections.sort(libraries);
        ArrayList<Library> signupOrd = new ArrayList<>();
        int days = 0;
        int i = 0;
        while (days < maxDays) {
            Library l = libraries.get(i++);
            int x = l.numDays;
            if (x + days <= maxDays) {
                days += x;
                signupOrd.add(l);
            } else {
                break;
            }
        }
        i = signupOrd.get(0).numDays;
        int next = 1;
        ArrayList<Library> signedUp = new ArrayList<>();
        HashSet<Integer> booksShipped = new HashSet<>();
        signedUp.add(signupOrd.get(0));
        for (int d = i; d < maxDays; d++) {
            if (d >= i + signupOrd.get(next).numDays) {
                i += signupOrd.get(next).numDays;
                signedUp.add(signupOrd.get(next++));
            }
            for()
        }
        // Step 4: Profit?
        System.out.print(libraries.get(0));
    }
}
