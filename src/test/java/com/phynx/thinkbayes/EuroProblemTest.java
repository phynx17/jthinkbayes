package com.phynx.thinkbayes;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * The Euro Problem Test case
 *
 * @author Pandu Pradhana
 *
 */
public class EuroProblemTest {

    @Test
    public void dataSet() {

        int max = 101;
        String hypos[] = new String[max];
        int i = 0;
        while (i < max) {
            hypos[i] = String.valueOf(i);
            i++;
        }

        EuroProblem ep = new EuroProblem(hypos);
        trainXTime(ep, "H", 140);
        trainXTime(ep, "T", 110);

        System.out.println(ep.getMaximumLikelihood());

        float mean = ep.mean();
        assert((int)mean == 55);
        String median = ep.percentile(50);
        assert(median.equals("56"));

        System.out.println("Mean: " + mean);
        System.out.println("Median: " + median);
        System.out.println("Credible Interval: " + ep.credibleInterval());


        System.out.println("====== Triangle Prior ======= ");

        TrianglePrior tp = new TrianglePrior(hypos);
        trainXTime(tp, "H", 140);
        trainXTime(tp, "T", 110);

        MaximumLikelihood ml = tp.getMaximumLikelihood();
        System.out.println(ml);

        mean = tp.mean();
        assert ((int) mean == 55);
        System.out.println("Mean: " + mean);
        System.out.println("Median: " + tp.percentile(50));

        System.out.println("Credible Interval: " + tp.credibleInterval());

        //tp.printMe(true);


        System.out.println("====== Collection Data Sets With Triangle Prior ======= ");

        final List<String> datasetsH = getDataSets("H",140);
        final List<String> datasetsT = getDataSets("T",110);

        TrianglePrior tpCollection = new TrianglePrior(hypos);
        tpCollection.updateCollectionProbability(datasetsH);
        //System.out.println("HAVE NORMALIZED 1");
        tpCollection.updateCollectionProbability(datasetsT);
        //System.out.println("HAVE NORMALIZED 2");

        //tpCollection.printMe();

        MaximumLikelihood ml2 = tpCollection.getMaximumLikelihood();
        System.out.println(ml2);
        assert(ml.probability == ml2.probability);
        assert (ml.value == ml2.value);

        mean = tpCollection.mean();
        assert ((int) mean == 55);
        System.out.println("Mean: " + mean);
        System.out.println("Median: " + tpCollection.percentile(50));
        System.out.println("Credible Interval: " + ml2);


        System.out.println("====== Beta Distribution ======= ");

        BetaDistribution bt = new BetaDistribution();
        bt.update(140,110);
        float betaMean = bt.mean();
        assert(((int)(betaMean*100)) == 55);
        System.out.println("Mean: " + betaMean);



    }


    /**
     * Train for X times
     * @param ep euro problem
     * @param data data
     * @param times how many times
     */
    private void trainXTime(EuroProblem ep, String data, int times) {
        for (int _i = 0; _i < times; _i++) {
            ep.updateProbability(data);
        }
    }


    /**
     * Get data sets
     * @param data the data
     * @param times how many times
     * @return the data sets
     */
    private List<String> getDataSets(String data, int times) {
        List<String> datasets = new ArrayList<String>();
        for (int _i = 0; _i < times; _i++) {
            datasets.add(data);
        }
        return datasets;
    }

}
