package com.phynx.thinkbayes;

/**
 * Taken from ThinkBayes by Allen B. Downey
 * <p/>
 * Chapter 4
 *
 * @author Pandu Pradhana
 *
 */
public class EuroProblem extends BayesianSuite {

    /**
     * Default constructor
     *
     * @param hypothesis
     */
    public EuroProblem(String[] hypothesis) {
        super(hypothesis);
    }


    @Override
    public float getLikelihood(String hypo, Object data) {
        float _xhypo = Float.valueOf(hypo).floatValue() / (float) 100;
        if (((String) data).equals("H")) return _xhypo;
        else return 1-_xhypo;
    }
}


/**
 * The case
 *
 */
class TrianglePrior extends  EuroProblem {

    /**
     * Default constructor
     *
     * @param hypothesis
     */
    public TrianglePrior(String[] hypothesis) {
        super(hypothesis);
        list.clear();
        int median = hypothesis.length/2;
        int l = 0;
        for (String __h : hypothesis) {
            int _x = Integer.parseInt(__h);
            if (l < median)
                setProbability(new DistributionValue(__h,_x));
            l++;
        }

        l = 0;
        for (String __h : hypothesis) {
            int _x = Integer.parseInt(__h);
            if (l >= median)
                setProbability(new DistributionValue(__h,100-_x));
            l++;
        }
        normalize();
    }


    @Override
    public float getLikelihood(String hypo, Object data) {
        float _xhypo = Float.valueOf(hypo).floatValue() / (float) 100;
        if (data instanceof int[] && ((int[]) data).length > 1) {
            /**
             * 2 dimension array
             */
            int sets[] = (int[]) data;
            return (float) (Math.pow(_xhypo,sets[0]) * Math.pow(1-_xhypo,sets[1]));
        } else {
            if (((String) data).equals("H")) return _xhypo;
            else return 1 - _xhypo;
        }
    }


}

