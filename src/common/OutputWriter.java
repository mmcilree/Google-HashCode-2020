package common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Bog standard Java file output. Take a table and write it exactly as is:
 * rows and columns.
 */
public class OutputWriter {
    public static void write(TabularData result, String filepath) {
        try {
            File myObj = new File(filepath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists... overwriting ;-).");
            }
            FileWriter myWriter = new FileWriter(myObj);
            for(int i = 0; i < result.getRowCount(); i++) {
                ArrayList<String> row = result.getRow(i);
                for(int j = 0; j < row.size(); j++) {
                    myWriter.write(row.get(j) + " ");
                }
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Whoops.");
            e.printStackTrace();
        }
    }
}
