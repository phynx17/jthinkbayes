package com.phynx.thinkbayes.locomotive;

/**
 * Taken from ThinkBayes by Allen B. Downey
 * <p/>
 * Chapter 3
 *
 * @author Pandu Pradhana
 */
public class LocomotiveAlternatePrior extends LocomotiveUniformPrior {

    /**
     * Multiplier (alpha) according to Power Law
     */
    private float alpha = 1.0f;

    /**
     * Constructor
     *
     * @param hypothesis
     */
    public LocomotiveAlternatePrior(String[] hypothesis) {
        this(hypothesis, 1.0f);
    }

    /**
     * Constructor
     * @param hypothesis
     * @param alpha
     */
    public LocomotiveAlternatePrior(String[] hypothesis, float alpha) {
        super(hypothesis);
        //need to clear (override the one in BayesianSuite
        clearDistributionList();
        this.alpha = alpha;
        this.hypothesis = hypothesis;
        for (String __h : hypothesis) {
            setProbability(new DistributionValue(__h,Float.valueOf(__h)));
        }
        normalize();

    }



    /**
     * Override the existing
     * @param val distribution value
     */
    public void setProbability(DistributionValue val) {
        assert(val != null);
        if (val.val.equals("0")) return;
        val.probability = (float)Math.pow(val.probability,(this.alpha*-1));
        //System.out.println(val.val + " --> " + val.probability);
        super.setProbability(val);
    }


}
