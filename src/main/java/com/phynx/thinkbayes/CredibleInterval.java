package com.phynx.thinkbayes;

/**
 * Represent the Credible Interval
 * (Chapter 3)
 *
 * @author Pandu Pradhana
 *
 */
public class CredibleInterval {

    /**
     * Lower bound
     */
    public String lowerBound;

    /**
     * Upper bound
     */
    public String upperBound;


    @Override
    public String toString() {
        return "[" + lowerBound + "," + upperBound + "]";
    }
}
