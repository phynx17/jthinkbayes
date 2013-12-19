package com.phynx.thinkbayes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Taken from ThinkBayes' PMF (Probability Mass Function)
 * By Allen B. Downey
 *
 * @author Pandu Pradhana
 * @since 11/6/13 12:51 AM
 */
public class PMF {
    /**
     * The list
     */
    protected List<DistributionValue> list = new ArrayList<DistributionValue>();

    /**
     * The Decimal Format
     */
    protected DecimalFormat decimalFormat = new DecimalFormat("0.####");

    /**
     * Set the probability
     * @param val distribution value
     */
    public void addProbability(DistributionValue val) {
        assert (val != null);
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
        printMe(false);
    }


    /**
     * Print info
     */
    public void printMe(boolean tabular) {
        StringBuilder sb =  null;
        if (tabular) sb = new StringBuilder("");
        else sb = new StringBuilder("{");

        int counter = 0;
        for (DistributionValue di : list) {
            if (!tabular) {
                if (counter > 0) sb.append(",");
                sb.append("{" + di.val + "," + di.probability + "}");
            } else {
                sb.append(di.val + "\t" + decimalFormat.format(di.probability));
                sb.append('\n');
            }
            counter++;
        }
        if (!tabular) sb.append("}");
        System.out.println(sb.toString());
    }


    /**
     * Normalize the whole probability
     */
    public void normalize() {
        final float totalOccurance = sumProbability();
        if (totalOccurance == 0) return;
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
     * Get all distribution values
     * @return distribution values
     */
    public List<DistributionValue> getAllDistributionValue() {
        return list;
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
     * Use in Cumulative distribution functions (CDF)
     * Get credible interval, getting the percentile of 5% and 95%
     *
     * @return
     */
    public CredibleInterval credibleInterval() {
        CredibleInterval ci = new CredibleInterval();
        ci.lowerBound = percentile(5);
        ci.upperBound = percentile(95);
        return ci;
    }


    /**
     * Use in Cumulative distribution functions (CDF)
     * Get percentile
     *
     * @param percentile if 0 return null
     * @return
     */
    public String percentile(int percentile) {
        if (percentile < 1) return null;
        float p = (float) percentile / (float) 100;
        float total = 0;
        for (int __i = 0; __i < list.size(); __i++) {
            DistributionValue __t = list.get(__i);
            total += __t.probability;
            if (total >= p) {
                return __t.val;
            }
        }
        return null;
    }


    /**
     * Get maximum likelihood
     * @return
     */
    public MaximumLikelihood getMaximumLikelihood() {
        MaximumLikelihood mlh = new MaximumLikelihood();
        float _tmpprob = 0;
        int _tmpval = 0;
        for (int __i = 0; __i < list.size(); __i++) {
            DistributionValue __t = list.get(__i);
            try {
                //int __tval = Integer.parseInt(__t.val);
                //if (__tval > _tmpval) _tmpval = __tval;
                if (__t.probability > _tmpprob) {
                    _tmpprob = __t.probability;
                    _tmpval = Integer.parseInt(__t.val);
                }
            } catch (Exception e) {
                //skip this
            }
        }
        mlh.value = _tmpval;
        mlh.probability = _tmpprob;
        return mlh;
    }


    /**
     * Get mean
     *
     * @return
     */
    public float mean() {
        float total = 0;
        for (DistributionValue di : list) {
            try {
                total += (Float.valueOf(di.val) * di.probability);
            } catch (Exception e) {
                //should not happend
            }
        }
        return total;
    }


    /**
     * Make CDF of maximum K selection of this item lists
     * @param k selection
     * @return the CDF
     */
    public CDF makeCDFMaxOfK(int k) {
        CDF cdf = makeCDF();
        for (DistributionValue value : cdf.getAllDistributionValue()) {
            value.probability = (float) Math.pow(value.probability,k);
        }
        return cdf;
    }



    /**
     * Get mqximum PMF
     *
     * @param aPMF a PMF
     * @param aOtherPMF other PMF
     * @return new max PMF
     */
    public static PMF pmfMax(PMF aPMF, PMF aOtherPMF) {
        assert(aPMF != null);
        assert (aOtherPMF != null);
        PMF maxPMF = new PMF();
        List<DistributionValue> that = aOtherPMF.getAllDistributionValue();
        for (DistributionValue di : aPMF.list) {
            for (DistributionValue thatValue : that) {
                int iMax = Math.max(Integer.parseInt(di.val),Integer.parseInt(thatValue.val));
                String val = String.valueOf(iMax);
                float totalProb = di.probability * thatValue.probability;
                DistributionValue lVal = maxPMF.getDistributionValue(val);
                if (lVal != null) {
                    lVal.probability += totalProb;
                } else {
                    maxPMF.addProbability(new DistributionValue(val, totalProb));
                }

            }
        }
        return maxPMF;
    }


    /**
     * Make CDF from this list
     * @return CDF
     */
    public CDF makeCDF() {
        float totalSum = sumProbability();

        List<DistributionValue> dist = new ArrayList<DistributionValue>();

        for (int __i = 0; __i < list.size(); __i++) {
            DistributionValue __t = list.get(__i);
            totalSum += __t.probability;
            DistributionValue _v = new DistributionValue(__t.val, totalSum);
            dist.add(_v);
        }
        for (int __i = 0; __i < dist.size(); __i++) {
            DistributionValue __t = dist.get(__i);
            __t.probability = __t.probability / totalSum;
        }
        return new CDF(dist);
    }



    /**
     * Sum probability for all distribution
     * @return
     */
    protected float sumProbability() {
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
