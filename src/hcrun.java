import common.*;
import practice.PracticeRunSpecifics;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Main class for HashCode problem solver. I've tried to make everything as general
 * as possible so we can adapt to whatever they happen to throw at us.
 *
 * I'm not particularly wedded to any of this code so if you think you can improve anything
 * please feel free.
 *
 * @author Matthew
 */
public class hcrun {
    public static void main(String[] args) {
        //Change this line for the run specifics of whatever HashCode problem we are attempting.
        RunSpecifics rs =  new PracticeRunSpecifics();

        //Date for appending to unnamed output.
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date now = new Date();

        TabularData input = new TabularData();
        Optimiser optimiser;
        String inputFile, outputFile;

        if (args.length < 1) {
            System.out.print("Need 1 arg for input file");
            return;
        }

        //The RunSpecifics needs to provide methods for how to choose the input file based
        //on the args and how to choose the optimiser (i.e. actual problem solver) for this problem.
        inputFile = rs.chooseFile(args[0]);

        if (args.length > 1) {
            optimiser = rs.chooseOptimiser(args[1]);
        } else {
            optimiser = rs.chooseOptimiser(null);
        }

        if (args.length > 2) {
            outputFile = rs.chooseOutputFile(args[2]);
        } else {
            //Default so we don't overwrite if no output file is specified
            outputFile = "./output/result_" + sdf.format(now) + ".txt";
        }

        //Now actually do the thing.
        InputReader.readFile(inputFile, input);
        TabularData result = optimiser.optimise(input);
        OutputWriter.write(result, outputFile);
    }
}
