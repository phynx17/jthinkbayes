package com.phynx.thinkbayes.locomotive;

import com.phynx.thinkbayes.BayesianSuite;

/**
 * Taken from ThinkBayes by Allen B. Downey
 *
 * Chapter 3
 *
 * Use for these typical problem:
 * - What did we know about N before we saw the data?
 * - For any given value of N, what is the likelihood of seeing the data (a locomotive with number 60)?
 *
 * @author Pandu Pradhana
 * @since 11/11/13 11:50 AM
 */
public class LocomotiveUniformPrior extends BayesianSuite {


    /**
     * Constructor
     * @param hypothesis
     */
    public LocomotiveUniformPrior(String[] hypothesis) {
        super(hypothesis);
    }


    /**
     * Get mean
     * @return
     */
    public int mean() {
        float total = 0;
        for (DistributionValue di : list) {
            try {
                total += (Float.valueOf(di.val) * di.probability);
            } catch (Exception e) {
                //should not happend
            }
        }
        return (int)total;
    }


    @Override
    public float getLikelihood(String hypo, Object data) {
        if (!(data instanceof java.lang.String))
            throw new IllegalArgumentException("Data must be java.lang.String");
        if (Integer.valueOf(hypo) < Integer.valueOf((String) data).intValue()) {
            return 0;
        } else {
            return (float) 1.0 / Float.valueOf(hypo).floatValue();
        }
    }
}
