package score;

import common.Constants;

public final class NiceScore {
    private double niceScore;
    private int bonus;

    public double getNiceScore() {
        return niceScore;
    }

    public int getBonus() {
        return bonus;
    }

    /**
     * Sets nice score value & handles score bonus
     * @param niceScore score value
     */
    public void setNiceScore(final double niceScore) {
        //  no bonus available
        if (bonus == 0) {
            //  set score
            this.niceScore = niceScore;
            return;
        }

        //  compute new score using the bonus

        //  compute new score based on bonus
        double newScore = niceScore + niceScore
                    * (double) bonus / Constants.PERCENT;

        //  cut down if it passes max average score
        if (newScore > (double) Constants.MAX_NICE_SCORE) {
            //  set score
            this.niceScore = Constants.MAX_NICE_SCORE;
            return;
        }

        //  set score
        this.niceScore = newScore;
    }

    public void setBonus(final int bonus) {
        this.bonus = bonus;
    }

    public static class Builder {
        private double niceScore;
        private int bonus;

        public Builder(final double niceScore) {
            this.niceScore = niceScore;
        }

        /**
         * Adds bonus to score
         * @param newBonus given bonus
         * @return score builder instance
         */
        public Builder addBonus(final int newBonus) {
            this.bonus = newBonus;
            return this;
        }

        /**
         * Gets new niceScore instance
         * @return  build niceScore instance
         */
        public NiceScore build() {
            return new NiceScore(this);
        }
    }
    private NiceScore(final Builder builder) {
        this.niceScore = builder.niceScore;
        this.bonus = builder.bonus;
    }
}
