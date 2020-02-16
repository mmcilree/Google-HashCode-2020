package practice;

import common.Optimiser;
import common.RunSpecifics;

/**
 * The run specifics for the 2020 practice problem.
 */
public class PracticeRunSpecifics extends RunSpecifics {
    @Override
    public String chooseFile(String arg) {
        String inputFile;

        switch(arg) {
            case "1":
                inputFile = "./input/a_example.in";
                break;
            case "2":
                inputFile = "./input/b_small.in";
                break;
            case "3":
                inputFile = "./input/c_medium.in";
                break;
            case "4":
                inputFile = "./input/d_quite_big.in";
                break;
            case "5":
                inputFile = "./input/e_also_big.in";
                break;
            default:
                inputFile = arg;
                break;
        }

        return inputFile;
    }

    @Override
    public Optimiser chooseOptimiser(String arg) {
        return new PracticeOptimiser();
    }

    @Override
    public String chooseOutputFile(String arg) {
        String inputFile;

        switch(arg) {
            case "1":
                inputFile = "./output/a_example_output.txt";
                break;
                /* etc */
            default:
                inputFile = "./output/" + arg;
                break;
        }

        return inputFile;
    }
}
