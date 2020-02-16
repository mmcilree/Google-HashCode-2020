package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Bog standard Java file reading. Doesn't really need its own class, but just to keep it
 * out of the way!
 */
public class InputReader {

    public static void readFile(String filename, TabularData data) {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data.parseLine(myReader.nextLine());
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Whoops.");
            e.printStackTrace();
        }

    }


}
