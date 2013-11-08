package com.phynx.thinkbayes;

import java.util.List;
import java.util.Map;

/**
 * Wrapper class for the M&M problem
 * FIX THIS, contains BUG :p
 *
 * @author Pandu Pradhana
 * @since 11/7/13 7:53 PM
 */
public class MandM extends BayesianSuite {

    /**
     * Default constructor
     *
     * @param hypothesis
     */
    public MandM(String[] hypothesis) {
        super(hypothesis);
    }


    /**
     * Update the probability of data, by multiplying with its likelihood. Also update
     * dataSet if given dataSet is set
     *
     * @param data
     */
    public void updateProbability(String data[]) {
        for (String hypo : this.hypothesis) {
            float likelihood = getLikelihood(hypo, data);
            multiplyProbability(hypo, likelihood);
        }
        normalize();
    }

    /**
     * Get Likelihood
     *
     * @param hypo
     * @param data
     * @return
     */
    public float getLikelihood(String hypo, Object data) {
        if (!(data instanceof java.lang.String[])) throw new IllegalArgumentException("Data must be java.lang.String[]");
        if (this.hypothesisSuite == null) {
            throw new IllegalArgumentException("Hypothesis suite cannot be null");
        }
        Map<String, List<DistributionValue>> li = (Map<String, List<DistributionValue>>)hypothesisSuite.get(hypo);
        for (DistributionValue d : li.get(((String[])data)[0])) {
            if (d.val.equals(((String[])data)[1])) return d.probability;
        }
        return 0;
    }

}
