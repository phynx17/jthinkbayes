package com.phynx.thinkbayes;

import org.junit.Test;

/**
 * Taken from ThinkBayes by Allen B. Downey
 * (Chapter 2)
 *
 * @author Pandu Pradhana
 * @since 11/8/13 4:32 PM
 */
public class MontyProblemTest {

    @Test
    public void montyTest() {
        final String[] hypothesis = {"A", "B", "C"};
        Monty monty = new Monty(hypothesis);
        monty.updateProbability("B");
        monty.printMe();
    }

}
