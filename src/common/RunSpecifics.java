package common;

/**
 * Class to override for specific choices on a particular problem.
 * Could use a switch based on arg value e.g. for choosing input:
 *      "1" -> "a_example.in"
 */
public abstract class RunSpecifics {
    public abstract String chooseFile(String arg);

    public abstract Optimiser chooseOptimiser(String arg);

    public abstract String chooseOutputFile(String arg);
}
