package com.phynx.thinkbayes;

import java.util.List;

/**
 * Taken from ThinkBayes by Allen B. Downey
 *
 * The Cookie Bayesian Framework
 *
 * @author Pandu Pradhana
 * @since 11/6/13 11:51 AM
 */
public class CookieBayesian extends BayesianSuite {

    /**
     * Constructor
     * @param hypothesis the hypothesis
     */
    public CookieBayesian(String[] hypothesis) {
        super(hypothesis);
        for (String __h : hypothesis) {
            addProbability(new DistributionValue(__h, 1));
        }
        normalize();
    }


    /**
     * Get likelihood from hypothesisSuite
     * @param hypo
     * @param data
     * @return
     */
    public float getLikelihood(String hypo, Object data) {
        if (!(data instanceof java.lang.String)) throw new IllegalArgumentException("Data must be java.lang.String");
        if (this.hypothesisSuite == null) throw new IllegalArgumentException("Hypothesis suite cannot be null");
        List<DistributionValue> li = (List<DistributionValue>)hypothesisSuite.get(hypo);
        for (DistributionValue d : li) {
            if (d.val.equals(data)) return d.probability;
        }
        return 0;
    }
}
