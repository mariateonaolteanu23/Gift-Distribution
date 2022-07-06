package strategy.score;

import common.Constants;
import receiver.Child;

public final class BabyScoreStrategy extends ScoreStrategy {
    public BabyScoreStrategy(final Child child) {
        super(child);
    }

    /**
     * Computes and sets the average score of a baby
     */
    @Override
    public void executeScoreStrategy() {
        //  first delivery (round 0)
        //  nice score history is empty
        if (getChild().scoreHistoryIsEmpty()) {
            //  add given nice score to the list (history) of nice scores
            getChild().getNiceScoreHistory().add(getChild().getNiceScore());

            //  average score will be the initial nice score + bonus
            getChild().setNiceScore(getChild().getNiceScore());
        }

        //  store score
        getChild().setNiceScore(Constants.MAX_NICE_SCORE);
    }
}
