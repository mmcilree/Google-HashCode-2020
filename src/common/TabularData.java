package common;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The problems tend to involve taking in and producing tables of numbers or strings:
 * so this class represents a table (might be a better way to do this but array lists can
 * get quite big without too much trouble so should be okay).
 *
 * I've put in some methods that might be useful: feel free to add more.
 */
public class TabularData {
    private ArrayList<ArrayList<String>> rows = new ArrayList<>();

    //When parsing rows as strings: what separates the elements?
    public final String SEPARATOR = " ";

    //Add a row to the table from a string.
    public void parseLine(String line) {
        String[] items = line.split(SEPARATOR);
        addRow(items);
    }

    public ArrayList<String> getRow(int row) {
        return rows.get(row);
    }
    public int getRowCount() {
        return rows.size();
    }

    public int getElementAsInt(int row, int column) {
        return Integer.parseInt(rows.get(row).get(column));
    }

    public ArrayList<Integer> getRowAsIntList(int row) {
        ArrayList<Integer> intList = new ArrayList<>();

        for(String s : rows.get(row)) {

            try {
                intList.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                //Asssume 0 if we can't read it for some reason.
                intList.add(0);
            }
        }

        return intList;

    }


    //Add a row to the table from an array (taking array of String)
    public void addRow(String[] items) {
        rows.add(new ArrayList<String>(Arrays.asList(items)));
    }

    //Add a row to the table from an array (take array of Int)
    public void addRow(ArrayList<String> items) {
        rows.add(new ArrayList<String>(items));
    }

    //Insert a row at a given position (taking array of String)
    public void insertRow(String[] items, int position) {
        rows.add(position, new ArrayList<String>(Arrays.asList(items)));
    }

    //Insert a row at a given position (taking array of int)
    public void insertRow(ArrayList<String> items, int position) {
        rows.add(position, new ArrayList<String>(items));
    }
}
