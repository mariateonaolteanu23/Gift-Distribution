package factory;

import common.Constants;
import receiver.Child;
import strategy.score.ScoreStrategy;

import java.lang.reflect.InvocationTargetException;

public final class ScoreStrategyFactory {
    private ScoreStrategyFactory() { }

    //  Gets class package and class name (e.g. strategy.score.BabyScoreStrategy)
    private static String getStrategyType(final String strategyType) {
        return Constants.SCORE_STRATEGY_PACKAGE
                + strategyType.charAt(0)
                + strategyType.substring(1).toLowerCase()
                + Constants.SCORE_STRATEGY;
    }

    /**
     * Creates a new ScoreStrategy instance based on child's status
     * (e.g. KidScoreStrategy)
     * @param child -> constructor parameter
     * @return new instance of ScoreStrategy class
     */
    public static ScoreStrategy createScoreStrategy(final Child child) {
        try {
            //  Constructor's parameter type
            var arguments = new Class[]{Child.class};
            //  Use child.getStatus().toString string to determine class name
            //  Call constructor of the class with the given argument
            //  Returns a new ScoreStrategy object
            return (ScoreStrategy) Class.forName(getStrategyType(child.getStatusType().toString()))
                            .getDeclaredConstructor(arguments).newInstance(child);
            //  Catch exceptions
        } catch (ClassNotFoundException | InvocationTargetException
                | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
