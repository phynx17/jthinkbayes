package com.phynx.thinkbayes;

import java.util.Map;

/**
 * Taken from ThinkBayes by Allen B. Downey
 *
 * Suites, is a model for the Framework
 *
 * @author Pandu Pradhana
 * @since 11/6/13 1:34 PM
 */
public abstract class BayesianSuite extends PMF {

    protected String[] hypothesis = null;
    protected Map<String, Object> hypothesisSuite = null;

    /* -- End of instance variables -- */


    /**
     * Default constructor
     * @param hypothesis
     */
    public BayesianSuite(String[] hypothesis) {
        super();
        if (hypothesis == null) throw new IllegalArgumentException("Hypothesis cannot be null");
        this.hypothesis = hypothesis;
        for (String __h : hypothesis) {
            setProbability(new DistributionValue(__h,1));
        }
        normalize();
    }



    /**
     * Set hypothesis suite
     * @param hypothesisSuite
     */
    public void setHypothesisSuite(Map<String, Object> hypothesisSuite) {
        this.hypothesisSuite = hypothesisSuite;
    }


    /**
     * Update the probability of data, by multiplying with its likelihood. Also update
     * dataSet if given dataSet is set
     * @param data
     */
    public void updateProbability(String data) {
        for (String hypo : this.hypothesis) {
            float likelihood = getLikelihood(hypo, data);
            multiplyProbability(hypo, likelihood);
        }
        normalize();
    }

    /**
     * Get Likelihood
     * @param hypo
     * @param data
     * @return
     */
    public abstract float getLikelihood(String hypo, Object data);


    /**
     * If we need to clear the distrubution list
     */
    protected void clearDistributionList() { list.clear();}

}
