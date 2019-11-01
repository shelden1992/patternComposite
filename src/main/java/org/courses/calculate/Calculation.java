package org.courses.calculate;

import org.apache.log4j.Logger;
import org.courses.parsing.EvaluateString;

public class Calculation {
    private static final Logger LOG = Logger.getLogger(Calculation.class);
    private static final Calculation INSTANCE = new Calculation();

    private Calculation() {
    }

    public static Calculation getInstance() {
        return INSTANCE;
    }

    public int calculateAndGet(String str) {
        LOG.debug("Calculation str " + str);
        return new EvaluateString().evaluate(str);
    }

}
