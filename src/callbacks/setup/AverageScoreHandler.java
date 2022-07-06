package callbacks.setup;

import common.DeliveryData;
import factory.ScoreStrategyFactory;
import receiver.Child;
import strategy.score.ScoreStrategy;

import java.util.List;

public final class AverageScoreHandler implements AnnualSetUpCallBack {
    /**
     * Sets the average score for every child
     */
    @Override
    public void set() {
        //  get list of children
        List<Child> children = DeliveryData.getDeliveryData().getChildren();
        for (Child child : children) {
            //  create a score strategy based on child's status
            ScoreStrategy scoreStrategy = ScoreStrategyFactory.createScoreStrategy(child);
            assert scoreStrategy != null;

            //  execute score strategy for child
            scoreStrategy.executeScoreStrategy();
        }
    }
}
