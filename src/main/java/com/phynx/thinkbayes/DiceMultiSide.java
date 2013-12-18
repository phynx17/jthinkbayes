package com.phynx.thinkbayes;

import java.util.HashMap;
import java.util.Map;

/**
 * Taken from ThinkBayes by Allen B. Downey
 *
 * Chapter 3
 *
 * @author Pandu Pradhana
 * @since 11/10/13 5:55 PM
 */
public class DiceMultiSide extends BayesianSuite {

    /**
     * Constructor
     * @param hypothesis
     */
    public DiceMultiSide(String[] hypothesis) {
        super(hypothesis);
    }


    @Override
    public float getLikelihood(String hypo, Object data) {
        if (!(data instanceof java.lang.String))
            throw new IllegalArgumentException("Data must be java.lang.String");

        //if (Integer.valueOf (hypo) < ((java.lang.Integer) data).intValue() ) {
        if (Integer.valueOf(hypo) < Integer.valueOf((String)data).intValue()) {
            return 0;
        } else {
            return (float) 1.0 / Float.valueOf(hypo).floatValue();
        }
    }
}
