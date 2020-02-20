package actual;

import common.Optimiser;
import common.RunSpecifics;

public class ActualRunSpecifics implements RunSpecifics {
    @Override
    public String chooseFile(String arg) {
        return "";
    }

    @Override
    public Optimiser chooseOptimiser(String arg) {
        return new ActualOptimiser();
    }

    @Override
    public String chooseOutputFile(String arg) {
        return "";
    }
}
