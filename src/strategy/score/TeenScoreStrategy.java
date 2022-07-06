package strategy.score;

import receiver.Child;

import java.util.List;

public final class TeenScoreStrategy extends ScoreStrategy {
    public TeenScoreStrategy(final Child child) {
        super(child);
    }

    /**
     * Computes and sets the average score of a teen
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
            double sum1 = 0;
            double sum2 = 0;

            for (int i = 0; i < scores.size(); ++i) {
                sum1 += scores.get(i) * (i + 1);
                sum2 += i + 1;
            }

            //  store average score
            getChild().setNiceScore(sum1 / sum2);
        }
    }
}
