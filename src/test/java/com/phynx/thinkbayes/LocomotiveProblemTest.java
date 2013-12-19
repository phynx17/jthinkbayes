package com.phynx.thinkbayes;

import com.phynx.thinkbayes.locomotive.LocomotiveAlternatePrior;
import com.phynx.thinkbayes.locomotive.LocomotiveUniformPrior;
import org.junit.Test;

/**
 * Taken from ThinkBayes by Allen B. Downey
 * (Chapter 3)
 *
 * @author Pandu Pradhana
 *
 */
public class LocomotiveProblemTest {

    @Test
    public void guessDataN() {

        String hypos[] = new String[1001];
        int i = 0;
        while (i < 1001) {
            hypos[i] = String.valueOf(i);
            i++;
        }

        LocomotiveUniformPrior lup = new LocomotiveUniformPrior(hypos);
        lup.updateProbability("60");
        //lup.printMe();
        float mean = lup.mean();
        assert((int)mean == 333);

        System.out.println("Posterior Mean: " + mean);

        lup.updateProbability("30");
        lup.updateProbability("90");

        mean = lup.mean();
        assert ((int) mean == 164);
        System.out.println("After trained, now Posterior Mean: " + mean);


        /**
         * Now the alternate
         */
        LocomotiveAlternatePrior lop = new LocomotiveAlternatePrior(hypos);
        lop.updateProbability("30");
        lop.updateProbability("60");
        lop.updateProbability("90");
        //lop.printMe();

        mean = lop.mean();
        assert ((int) mean == 133);
        System.out.println("Alternate Prior Posterior Mean: " + mean);


        CredibleInterval ci = lop.credibleInterval();
        String credible5 = ci.lowerBound;
        String credible95 = ci.upperBound;

        assert(credible5.equals("91"));
        System.out.println("Credible Interval (5%): " + credible5);
        assert (credible95.equals("242"));
        System.out.println("Credible Interval (95%): " + credible95);

    }

}
