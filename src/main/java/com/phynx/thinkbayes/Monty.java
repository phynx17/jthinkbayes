package com.phynx.thinkbayes;

/**
 * Taken from ThinkBayes by Allen B. Downey
 *
 * Wrapper class for the Monty problem
 *
 * @author Pandu Pradhana
 * @since 11/7/13 7:41 PM
 */
public class Monty extends BayesianSuite {

    /**
     * Default constructor
     *
     * @param hypothesis
     */
    public Monty(String[] hypothesis) {
        super(hypothesis);
    }


    @Override
    public float getLikelihood(String hypo, Object data) {
        if (!(data instanceof java.lang.String)) throw new IllegalArgumentException("Data must be java.lang.String");
        if (hypo != null && data != null && hypo.equals(data)) return 0;
        else if ("A".equals(hypo)) return (float)0.5;
        else return 1;
    }
}
