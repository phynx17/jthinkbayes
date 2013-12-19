package com.phynx.thinkbayes;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * The odds and addends
 *
 * (Chapter 5)
 *
 * @author Pandu Pradhana
 *
 */
public class OddsAndAddendsTest {


    @Test
    public void experiments() {

        final float prob = 0.75f;

        float odds = Tools.odds(prob);
        System.out.println("Odds: " + odds);

        float probability = Tools.probabilityFromOdds(odds);
        System.out.println("Probability: " + probability);

        assert(probability == prob);
    }


    @Test
    public void dice() {

        List<Die> dieDataSet = new ArrayList<Die>();

        System.out.println("=========== Sample ============");

        Die satu = new Die(6);
        dieDataSet.add(satu);
        Die dua = new Die(6);
        dieDataSet.add(dua);
        Die tiga = new Die(6);
        dieDataSet.add(tiga);

        PMF threeDie = Die.createSampleRandomSum(dieDataSet,1000);
        threeDie.printMe();

        System.out.println("=========== EXACT ============");


        Die allAdded = satu.addDie(dua).addDie(tiga);
        allAdded.normalize();

        allAdded.printMe();


        System.out.println("=========== MAX PMF ============");

        //This is the Hard way
        //PMF maxBestAttr1 = PMF.pmfMax(allAdded,allAdded);

        CDF cdf = allAdded.makeCDFMaxOfK(6);
        //cdf.printMe(true);
        PMF pmf = cdf.makePMF();
        //pmf.normalize();
        pmf.printMe(true);

    }
}
