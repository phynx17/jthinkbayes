package com.phynx.thinkbayes;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Taken from ThinkBayes by Allen B. Downey
 * (Chapter 2)
 *
 * @author Pandu Pradhana
 * @since 11/8/13 4:12 PM
 */
public class CookieProblemTest {

    @Test
    public void cookieJarTest() {
        final String[] hypothesis = {"Bowl 1", "Bowl 2"};

        PMF pmf = new PMF();
        pmf.addProbability(new PMF.DistributionValue(hypothesis[0], (float) 0.5));
        pmf.addProbability(new PMF.DistributionValue(hypothesis[1], (float) 0.5));

        pmf.printMe();

        //assertTrue(pmf)

        //Update the distribution
        pmf.multiplyProbability(hypothesis[0], (float) 0.75);
        pmf.multiplyProbability(hypothesis[1], (float) 0.5);

        pmf.normalize();

        System.out.println("After normallize");

        pmf.printMe();

        System.out.println("Entering the Bayesian Framework");

        /**
         * Now the bayesian way
         *
         */
        CookieBayesian cp = new CookieBayesian(hypothesis);

        //Map<String, List<PMF.DistributionValue>> hypoSuites = new HashMap<String, List<PMF.DistributionValue>>();
        Map<String, Object> hypoSuites = new HashMap<String, Object>();


        List<PMF.DistributionValue> distValuesBowl1 = new ArrayList<PMF.DistributionValue>();
        distValuesBowl1.add(new PMF.DistributionValue("vanilla", (float) 0.75));
        distValuesBowl1.add(new PMF.DistributionValue("chocolate", (float) 0.25));

        List<PMF.DistributionValue> distValuesBowl2 = new ArrayList<PMF.DistributionValue>();
        distValuesBowl2.add(new PMF.DistributionValue("vanilla", (float) 0.5));
        distValuesBowl2.add(new PMF.DistributionValue("chocolate", (float) 0.5));

        hypoSuites.put("Bowl 1", distValuesBowl1);
        hypoSuites.put("Bowl 2", distValuesBowl2);

        cp.setHypothesisSuite(hypoSuites);

        cp.updateProbability("vanilla");
        /*
        cp.updateProbability("chocolate");
        cp.updateProbability("chocolate");
        cp.updateProbability("chocolate");

        cp.updateProbability("vanilla");
        cp.updateProbability("vanilla");
        cp.updateProbability("vanilla");
        cp.updateProbability("vanilla");
        cp.updateProbability("vanilla");
        cp.updateProbability("vanilla");
        */
        cp.printMe();


    }

}
