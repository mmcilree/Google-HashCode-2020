package common;

import java.util.ArrayList;
import java.util.Arrays;

public class TabularData {
    private ArrayList<ArrayList<String>> rows = new ArrayList<>();

    public final String SEPARATOR = " ";

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
    protected void addRow(String[] items) {
        rows.add(new ArrayList<String>(Arrays.asList(items)));
    }
}
