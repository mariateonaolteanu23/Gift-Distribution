package strategy.score;

import receiver.Child;

import java.util.List;

public final class KidScoreStrategy extends ScoreStrategy {
    public KidScoreStrategy(final Child child) {
        super(child);
    }

    /**
     * Computes and sets the average score of a kid
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
        } else {
            //  compute average score using the given formula
            List<Double> scores = getChild().getNiceScoreHistory();
            double score = scores.stream().mapToDouble(s -> s).sum() / scores.size();

            //  store average score
            getChild().setNiceScore(score);
        }
    }
}
