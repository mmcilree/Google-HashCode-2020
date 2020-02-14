import common.*;
import practice.PracticeRunSpecifics;

import java.text.SimpleDateFormat;
import java.util.Date;

public class hcrun {
    public static void main(String[] args) {
        //Only line that should change
        RunSpecifics rs =  new PracticeRunSpecifics();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date now = new Date();

        TabularData input = new TabularData();
        Optimiser optimiser;
        String inputFile, outputFile;

        if (args.length < 1) {
            System.out.print("Need 1 arg for input file");
            return;
        }

        inputFile = rs.chooseFile(args[0]);

        if (args.length > 1) {
            optimiser = rs.chooseOptimiser(args[1]);
        } else {
            optimiser = rs.chooseOptimiser(null);
        }

        if (args.length > 2) {
            outputFile = rs.chooseOutputFile(args[2]);
        } else {
            outputFile = "./output/result_" + sdf.format(now) + ".txt";
        }


        InputReader.readFile(inputFile, input);
        TabularData result = optimiser.optimise(input);
        OutputWriter.write(result, outputFile);
    }
}
