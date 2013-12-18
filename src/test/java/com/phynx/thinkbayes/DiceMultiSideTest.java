package com.phynx.thinkbayes;

import org.junit.Test;

/**
 * Taken from ThinkBayes by Allen B. Downey
 * (Chapter 3)
 *
 * @author Pandu Pradhana
 *
 */
public class DiceMultiSideTest {

    @Test
    public void diceTest() {

        String[] dice = {"4","6","8","12","20"};
        DiceMultiSide dm = new DiceMultiSide(dice);

        dm.updateProbability("6");
        dm.printMe();


        dm.updateProbability("6");
        dm.updateProbability("8");
        dm.updateProbability("7");
        dm.updateProbability("7");
        dm.updateProbability("5");
        dm.updateProbability("4");
        dm.printMe();


    }

}
