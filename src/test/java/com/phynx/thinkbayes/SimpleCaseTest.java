package com.phynx.thinkbayes;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Taken from ThinkBayes by Allen B. Downey
 * (Chapter 2)
 *
 * @author Pandu Pradhana
 * @since 11/8/13 4:29 PM
 */
public class SimpleCaseTest {

    @Test
    public void simpleTest() {
        PMF pmf = new PMF();
        /**
         * Chapter 2
         */
        /*
        final int diceSide = 6;
        for (int x= 1; x < (diceSide+1); x++) {
            pmf.setProbability(new PMF.DistributionValue(String.valueOf(x),(float) 1/diceSide));
        }
        */

        String[] wordlist = {"ada", "apa", "dengan", "mu", "ada", "ada"};
        for (String word : wordlist) {
            pmf.increaseOccurance(word, 1);
        }

        pmf.printMe();
        pmf.normalize();
        pmf.printMe();
        //String testVal = "dengan";
        String testVal = "ada";

        assertTrue(pmf.getProbabilityOf(testVal) == (float) 0.5);

        System.out.println("Probability Of (" + testVal + "):" + pmf.getProbabilityOf(testVal));


    }

}
