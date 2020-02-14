package common;

public abstract class RunSpecifics {
    public abstract String chooseFile(String arg);

    public abstract Optimiser chooseOptimiser(String arg);

    public abstract String chooseOutputFile(String arg);
}
