package com.phynx.thinkbayes;

/**
 * Beta Distribution
 * http://en.wikipedia.org/wiki/Beta_distribution
 *
 * (Chapter 3)
 *
 * @author Pandu Pradhana
 *
 */
public class BetaDistribution {

    private int alpha;
    private int beta;

    /**
     * Empty constructor, with uniform distribution (1)
     *
     */
    public BetaDistribution() {
        this(1,1);
    }


    /**
     * Constructor with define alpha and beta
     * @param alpha the alpha value
     * @param beta the beta value
     *
     */
    public BetaDistribution(int alpha, int beta) {
        this.alpha = alpha;
        this.beta = beta;
    }


    /**
     * Update
     * @param head
     * @param tail
     */
    public void update(int head, int tail) {
        this.alpha += head;
        this.beta += tail;
    }


    /**
     * Get Mean
     * @return
     */
    public float mean() {
        return (float)this.alpha / ((float) this.alpha + this.beta);
    }

}
