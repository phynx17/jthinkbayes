package com.phynx.thinkbayes;

/**
 * Maximum Likelihood
 *
 * @author Pandu Pradhana
 * @since 12/18/13 4:36 PM
 */
public class MaximumLikelihood {

    /**
     * Probability
     */
    public float probability;

    /**
     * The Value
     */
    public int value;


    @Override
    public String toString() {
        return "Maximum Value: " + value + "\tMaximum Probability: " + probability;
    }
}
