package main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import actual.ActualRunSpecifics;
import common.InputReader;
import common.Optimiser;
import common.OutputWriter;
import common.RunSpecifics;
import common.TabularData;
import practice.PracticeRunSpecifics;

/**
 * Main class for HashCode problem solver. I've tried to make everything as
 * general as possible so we can adapt to whatever they happen to throw at us.
 *
 * I'm not particularly wedded to any of this code so if you think you can
 * improve anything please feel free.
 *
 * @author Matthew
 */
public class hcrun {
    // Change this line for the run specifics of whatever HashCode problem we are
    // attempting.
    private static RunSpecifics rs = new ActualRunSpecifics();

    public static void main(String[] args) {
        // Date for appending to unnamed output.
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date now = new Date();

        TabularData input = new TabularData();

        // Empty string = Default optimiser
        Optimiser optimiser = rs.chooseOptimiser("");
        String inputFile, outputFile;

        if (args.length < 1) {
            System.out.print("No args. Running on everything in ./input...");
            runAll(input, optimiser);
            return;
        }

        if (args[0] == "all") {
            runAll(input, optimiser);
        }
        // The RunSpecifics needs to provide methods for how to choose the input file
        // based
        // on the args and how to choose the optimiser (i.e. actual problem solver) for
        // this problem.
        inputFile = rs.chooseFile(args[0]);

        if (args.length > 1) {
            outputFile = rs.chooseOutputFile(args[1]);
        } else {
            // Default so we don't overwrite if no output file is specified
            outputFile = "./output/result_" + sdf.format(now) + ".txt";
        }

        if (args.length > 2) {
            optimiser = rs.chooseOptimiser(args[2]);
        } else {
            optimiser = rs.chooseOptimiser(null);
        }

        runOnce(inputFile, optimiser, outputFile, input);
    }

    private static void runOnce(String inputFile, Optimiser optimiser, String outputFile, TabularData input) {
        // Now actually do the thing.
        InputReader.readFile(inputFile, input);
        TabularData result = optimiser.optimise(input);
        OutputWriter.write(result, outputFile);
    }

    // Alternatively... just choose default optimiser for these run specifics
    // and then run on every input file in the input folder.
    private static void runAll(TabularData input, Optimiser optimiser) {
        optimiser = rs.chooseOptimiser("");

        File folder = new File("./input");
        File[] listOfFiles = folder.listFiles();

        for (File f : listOfFiles) {
            // Regex gets rid of the extension
            InputReader.readFile("./input/" + f.getName(), input);
            TabularData result = optimiser.optimise(input);
            OutputWriter.write(result, "./output/" + f.getName().replaceFirst("[.][^.]+$", "") + "_out.txt");
        }

    }
}
