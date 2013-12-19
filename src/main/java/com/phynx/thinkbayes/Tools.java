package com.phynx.thinkbayes;

/**
 * Set of independent methods
 *
 * @author Pandu Pradhana
 *
 */
public class Tools {

    /**
     * Compute odds
     * @param probablity the probability
     * @return odds
     *
     */
    public static final float odds(float probablity) {
        return probablity / (1-probablity);
    }


    /**
     * Get probability from odds
     * @param odds
     * @return
     */
    public static final float probabilityFromOdds(float odds) {
        return odds / (1 + odds);
    }


}
