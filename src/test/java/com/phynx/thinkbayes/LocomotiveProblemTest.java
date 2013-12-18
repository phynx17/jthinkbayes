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
        System.out.println("Posterior Mean: " + lup.mean());


        lup.updateProbability("30");
        lup.updateProbability("90");
        System.out.println("After trained, now Posterior Mean: " + lup.mean());


        /**
         * Now the alternate
         */
        LocomotiveAlternatePrior lop = new LocomotiveAlternatePrior(hypos);
        lop.updateProbability("30");
        lop.updateProbability("60");
        lop.updateProbability("90");
        //lop.printMe();
        System.out.println("Alternate Prior Posterior Mean: " + lop.mean());


        String credible5 = lop.credibleInterval(5);
        String credible95 = lop.credibleInterval(95);

        System.out.println("Credible Interval (5%): " + credible5);
        System.out.println("Credible Interval (95%): " + credible95);

    }

}
