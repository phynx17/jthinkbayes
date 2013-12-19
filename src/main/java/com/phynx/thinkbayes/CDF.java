package com.phynx.thinkbayes;

import java.util.ArrayList;
import java.util.List;

/**
 * CDF (Cumulative distribution functions)
 *
 * @author Pandu Pradhana
 *
 */
public class CDF extends PMF {

    /**
     * Default constructor
     */
    public CDF() {}

    /**
     * Constructor with distribution values
     * @param distributionValues distribution values
     */
    public CDF(List<DistributionValue> distributionValues) {
        this.list = distributionValues;
    }


    /**
     * Make PMF from this object
     * @return PMF
     */
    public PMF makePMF() {
        PMF pmf = new PMF();
        float prev = 0.0f;
        for (int __i = 0; __i < this.list.size(); __i++) {
            PMF.DistributionValue __t = this.list.get(__i);
            if (__t != null) {
                float __tmpProb = __t.probability;
                __t.probability = __t.probability - prev;
                prev = __tmpProb;
            }
            //BUG:
            pmf.addProbability(__t);
        }
        return pmf;
    }


    /**
     * This method is overridden to be NOT implemented
     * @return
     */
    public CDF makeCdf() {
        return null;
    }

}
