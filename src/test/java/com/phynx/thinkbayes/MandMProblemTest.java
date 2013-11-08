package com.phynx.thinkbayes;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Taken from ThinkBayes by Allen B. Downey
 * (Chapter 2)
 *
 * @author Pandu Pradhana
 * @since 11/8/13 4:34 PM
 */
public class MandMProblemTest {

    @Test
    public void problemTest() {
        final String[] hypothesis = {"hypoA", "hypoB"};

        List<PMF.DistributionValue> distValuesBowl1 = new ArrayList<PMF.DistributionValue>();
        distValuesBowl1.add(new PMF.DistributionValue("brown", (float) 30));
        distValuesBowl1.add(new PMF.DistributionValue("yellow", (float) 20));
        distValuesBowl1.add(new PMF.DistributionValue("red", (float) 20));
        distValuesBowl1.add(new PMF.DistributionValue("green", (float) 10));
        distValuesBowl1.add(new PMF.DistributionValue("orange", (float) 10));
        distValuesBowl1.add(new PMF.DistributionValue("tan", (float) 10));

        List<PMF.DistributionValue> distValuesBowl2 = new ArrayList<PMF.DistributionValue>();
        distValuesBowl2.add(new PMF.DistributionValue("blue", (float) 24));
        distValuesBowl2.add(new PMF.DistributionValue("green", (float) 20));
        distValuesBowl2.add(new PMF.DistributionValue("orange", (float) 16));
        distValuesBowl2.add(new PMF.DistributionValue("yellow", (float) 14));
        distValuesBowl2.add(new PMF.DistributionValue("red", (float) 13));
        distValuesBowl2.add(new PMF.DistributionValue("brown", (float) 13));

        Map<String, List<PMF.DistributionValue>> hypoSuitesA = new HashMap<String, List<PMF.DistributionValue>>();
        hypoSuitesA.put("bag1", distValuesBowl1);
        hypoSuitesA.put("bag2", distValuesBowl2);


        Map<String, List<PMF.DistributionValue>> hypoSuitesB = new HashMap<String, List<PMF.DistributionValue>>();
        hypoSuitesB.put("bag1", distValuesBowl2);
        hypoSuitesB.put("bag2", distValuesBowl1);


        Map<String, Object> allHypoSuites = new HashMap<String, Object>();

        allHypoSuites.put("hypoA", hypoSuitesA);
        allHypoSuites.put("hypoB", hypoSuitesB);

        MandM m = new MandM(hypothesis);
        m.setHypothesisSuite(allHypoSuites);

        String[] satu = {"bag1", "yellow"};
        m.updateProbability(satu);

        String[] dua = {"bag2", "green"};
        m.updateProbability(dua);

        //System.out.println("--> " +  ((float)20 / (float)27));
        //System.out.println("--> " + m.getProbabilityOf("hypoA"));
        //assertTrue(m.getProbabilityOf("hypoA") == (float) 20 / 27);

        m.printMe();

    }
}
