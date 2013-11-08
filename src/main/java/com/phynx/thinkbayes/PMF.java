package com.phynx.thinkbayes;

import java.util.ArrayList;

/**
 * Taken from ThinkBayes' PMF (Probability Mass Function)
 * By Allen B. Downey
 *
 * @author Pandu Pradhana
 * @since 11/6/13 12:51 AM
 */
public class PMF {
    private ArrayList<DistributionValue> list = new ArrayList<DistributionValue>();
    //private int totalOccurance = 0;


    /**
     * Set the probability
     * @param val distribution value
     */
    public void setProbability(DistributionValue val) {
        list.add(val);
    }


    /**
     * Increase occurance by
     * @param val
     * @param increaseBy
     */
    public void increaseOccurance(String val, int increaseBy) {
        /*
        int idx = list.indexOf(val);
        System.out.println(val + ": " + idx);
        if (idx > -1) {
            list.get(idx).probability =+ increaseBy;
        } else list.add(new DistributionValue(val, increaseBy));
        */

        DistributionValue lVal = getDistributionValue(val);
        if (lVal == null) {
            lVal = new DistributionValue(val, 0);
            list.add(lVal);
        }
        lVal.probability += increaseBy;
        //totalOccurance += lVal.probability;
    }


    /**
     * Multiply probability
     * @param val
     * @param probability
     */
    public void multiplyProbability(String val, float probability) {
        DistributionValue lVal = getDistributionValue(val);
        if (lVal == null) {
            lVal = new DistributionValue(val, probability);
            list.add(lVal);
        }
        lVal.probability *= probability;
    }


    /**
     * Print info
     */
    public void printMe() {
        StringBuilder sb = new StringBuilder("{");
        int counter = 0;
        for (DistributionValue di : list) {
            if (counter > 0) sb.append(",");
            sb.append("{" + di.val + "," + di.probability + "}");
            counter++;
        }
        sb.append("}");
        System.out.println(sb.toString());
    }


    /**
     * Normalize the whole probability
     */
    public void normalize() {
        final float totalOccurance = sumProbability();
        for (DistributionValue di : list) {
            di.probability = di.probability/totalOccurance;
        }
    }

    /**
     * Get probability of a value
     * @param val
     * @return
     */
    public float getProbabilityOf(String val) {
        for (int __i = 0; __i < list.size(); __i++) {
            DistributionValue __t = list.get(__i);
            if (__t.val.equals(val)) return __t.probability;
        }
        return 0;
    }


    /**
     * Get distribution value of given value
     * @param val value
     * @return
     */
    public DistributionValue getDistributionValue(String val) {
        for (int __i = 0; __i < list.size(); __i++) {
            DistributionValue __t = list.get(__i);
            if (__t.val.equals(val)) {
                return __t;
            }
        }
        return null;
    }


    /**
     * Sum probability for all distribution
     * @return
     */
    private float sumProbability() {
        float sum = 0;
        for (int __i = 0; __i < list.size(); __i++) {
            DistributionValue __t = list.get(__i);
            sum += (float)__t.probability;
        }
        return sum;
    }



    /**
     * Helper class
     */
    public static class DistributionValue {
        public final String val;
        public float probability;
        public DistributionValue(String val, float probability) {
            this.val = val;
            this.probability = probability;
            if (this.val == null) throw new IllegalArgumentException("Val cannot be null");
        }

        /**
         * Only checks for the val
         * @param a
         * @return
         */
        public boolean equals(Object a) {
            if (a == null) {
                return false;
            }
            if (a instanceof DistributionValue) {
                return ((DistributionValue)a).val.equals(this.val);
            } else if (a instanceof java.lang.String) {
                return this.val.equals(a);
            }
            else {
                return false;
            }
        }
    }

}
