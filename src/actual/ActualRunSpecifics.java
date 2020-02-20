package actual;

import common.Optimiser;
import common.RunSpecifics;

public class ActualRunSpecifics implements RunSpecifics {
    @Override
    public String chooseFile(String arg) {
        if(arg == "d") {
            return "./input/d_tough_choices";
        } else {
            return "";
        }
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
