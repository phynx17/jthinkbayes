package com.phynx.thinkbayes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Taken from ThinkBayes by Allen B. Downey
 *
 * (Chapter 5)
 *
 * @author Pandu Pradhana
 *
 */
public class Die extends PMF {

    protected int[] dieSides = null;

    /**
     * Empty constructor
     */
    public Die() {}

    /**
     * Default constructor
      * @param sides how many sides of the die
     */
    public Die(int sides) {
        super();
        dieSides = new int[sides];
        for (int i=0; i< sides;i++) {
            int currentSide = i+1;
            dieSides[i] = currentSide;
            addProbability(new DistributionValue(String.valueOf(currentSide), 1));
        }
        normalize();
    }

    /**
     * Get die sides
     * @return how many sides of die
     */
    public int getSides() { return dieSides.length; }


    /**
     * Add die
     * @param other other dice
     * @return new die
     */
    public Die addDie(Die other) {
        Die addedDie = new Die();
        List<DistributionValue> that = other.getAllDistributionValue();
        for (DistributionValue di : this.list) {
            for (DistributionValue thatValue : that) {
                String val = String.valueOf(Integer.parseInt(di.val) + Integer.parseInt(thatValue.val));
                float totalProb = di.probability + thatValue.probability;
                DistributionValue lVal = addedDie.getDistributionValue(val);
                if (lVal != null) {
                    lVal.probability += totalProb;
                } else {
                    addedDie.addProbability(new DistributionValue(val, totalProb));
                }

            }
        }
        return addedDie;
    }



    /**
     * The Random seeds
     */
    private static final Random random = new Random();

    /**
     * Create PMF sample of random summary of die
     * @param dies list of die
     * @param sampleCount how many sample
     * @return
     */
    public static PMF createSampleRandomSum(List<Die> dies, int sampleCount) {
        PMF pmf = new PMF();
        for (int i = 0; i < sampleCount; i++) {
            String ran = String.valueOf(getRandomSum(dies));
            DistributionValue lVal = pmf.getDistributionValue(ran);
            if (lVal != null) {
                lVal.probability += 1;
            } else {
                pmf.addProbability(new DistributionValue(ran, 1));
            }

        }
        pmf.normalize();
        return pmf;
    }

    /**
     * Get summary of random die
     * @param dies
     * @return
     */
    static int getRandomSum(final List<Die> dies) {
        int total = 0;
        for (Die die : dies) {
            total += random.nextInt(die.getSides());
        }
        return total;
    }


    /**
     * Create PMF sample of random summary of die
     *
     * @param dies        list of die
     * @param sampleCount how many sample
     * @return
     */
    public static PMF createSampleRandomMax(List<Die> dies, int sampleCount) {
        PMF pmf = new PMF();
        for (int i = 0; i < sampleCount; i++) {
            String ran = String.valueOf(getRandomMax(dies));
            DistributionValue lVal = pmf.getDistributionValue(ran);
            if (lVal != null) {
                lVal.probability += 1;
            } else {
                pmf.addProbability(new DistributionValue(ran, 1));
            }

        }
        pmf.normalize();
        return pmf;
    }

    /**
     * Get summary of random die
     *
     * @param dies
     * @return
     */
    static int getRandomMax(final List<Die> dies) {
        int max = 0;
        for (Die die : dies) {
            int _tmp = random.nextInt(die.getSides());
            if (_tmp > max) max = _tmp;
        }
        return max;
    }


}
