package common;

/**
 * Class to override for specific choices on a particular problem.
 * Could use a switch based on arg value e.g. for choosing input:
 *      "1" -> "a_example.in"
 */
public interface RunSpecifics {
    String chooseFile(String arg);


    Optimiser chooseOptimiser(String arg);

    String chooseOutputFile(String arg);
}
