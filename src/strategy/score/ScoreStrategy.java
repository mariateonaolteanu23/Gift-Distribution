package strategy.score;

import receiver.Child;

public abstract class ScoreStrategy {
    private Child child;

    public ScoreStrategy(final Child child) {
        this.child = child;
    }

    /**
     * Gets the child on which the score strategy will be applied
     * @return child
     */
    public Child getChild() {
        return child;
    }

    /**
     * Computes the average score of a child based on the child's status
     */
    public abstract void executeScoreStrategy();
}
