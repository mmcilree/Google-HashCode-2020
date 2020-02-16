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

    //Add a row to the table from an array: protected at the moment but
    //could be public if necessary.
    protected void addRow(String[] items) {
        rows.add(new ArrayList<String>(Arrays.asList(items)));
    }
}
