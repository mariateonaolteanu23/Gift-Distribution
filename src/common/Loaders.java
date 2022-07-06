package common;


import callbacks.setup.AverageScoreHandler;
import callbacks.setup.BudgetUnitHandler;
import callbacks.setup.ChildrenStatusHandler;
import callbacks.setup.IdSortHandler;
import callbacks.setup.ChildBudgetHandler;
import callbacks.update.ChildrenAgeUpdater;
import callbacks.update.ChildrenGiftHistoryEraser;
import callbacks.update.CurrentDateUpdater;
import callbacks.update.InputDataUpdater;
import delivery.DeliveryAction;
import delivery.DeliveryPlanner;
import fileio.input.AnnualChangeInputData;
import fileio.input.Input;
import receiver.Child;

import strategy.delivery.IdDeliveryStrategy;
import strategy.delivery.NiceScoreCityDeliveryStrategy;
import strategy.delivery.NiceScoreDeliveryStrategy;
import strategy.score.BabyScoreStrategy;
import strategy.score.KidScoreStrategy;
import strategy.score.TeenScoreStrategy;
import strategy.score.YoungAdultScoreStrategy;

import java.util.List;

public final class Loaders {
    private Loaders() { }

    /**
     * Loads input data into delivery database
     * @param input -> input data
     */
    public static void loadDeliveryData(final Input input) {
        DeliveryData.getDeliveryData().setCurrentYear(0);
        DeliveryData.getDeliveryData().setSantaBudget(input.getSantaBudget());
        DeliveryData.getDeliveryData().setChildren(input.getChildren());
        DeliveryData.getDeliveryData().setGifts(input.getGifts());
        DeliveryData.getDeliveryData().setCities(input.getCities());
        DeliveryData.getDeliveryData().setDeliveryStrategy(Constants.ID);
    }

    /**
     * Adds all annual updates that will be executed every year after delivery
     * @param deliveryPlanner -> entity that handles all deliveries
     *                        over the course of specified years
     * @param annualChanges -> input list of changes mapped for every year
     */
    public static void registerAnnualUpdates(final DeliveryPlanner deliveryPlanner,
                                             final List<AnnualChangeInputData> annualChanges) {
        deliveryPlanner.registerAnnualUpdates(new ChildrenAgeUpdater());
        deliveryPlanner.registerAnnualUpdates(new InputDataUpdater(annualChanges));
        deliveryPlanner.registerAnnualUpdates(new ChildrenGiftHistoryEraser());
        deliveryPlanner.registerAnnualUpdates(new CurrentDateUpdater());
    }

    /**
     * Adds all annual set-ups that will be executed every year before delivery
     * @param deliveryAction -> entity that handles an annual delivery
     */
    public static void registerAnnualSetUps(final DeliveryAction deliveryAction) {
        deliveryAction.registerAnnualSetUp(new IdSortHandler());
        deliveryAction.registerAnnualSetUp(new ChildrenStatusHandler());
        deliveryAction.registerAnnualSetUp(new AverageScoreHandler());
        deliveryAction.registerAnnualSetUp(new BudgetUnitHandler());
        deliveryAction.registerAnnualSetUp(new ChildBudgetHandler());
    }
    /**
     * NOTE: ADAPTATION FOR DESIGNATED CHECKER TOOL
     * The following methods are never used in the actual implementation!
     * Vmchecker doesn't load classes the same way as the local machine
     * More details in README file
     */

    /**
     *Instantiates all DeliveryStrategy subclasses
     * @param strategy constructor parameter
     */
    public static void loadDeliveryStrategies(final String strategy) {
        IdDeliveryStrategy idStrategy = new IdDeliveryStrategy(strategy);
        NiceScoreCityDeliveryStrategy cityStrategy = new NiceScoreCityDeliveryStrategy(strategy);
        NiceScoreDeliveryStrategy niceScoreStrategy = new NiceScoreDeliveryStrategy(strategy);
    }

    /**
     * Instantiates all ScoreStrategy subclasses
     * @param child constructor parameter
     */
    public static void loadScoreStrategies(final Child child) {
        BabyScoreStrategy babyScoreStrategy = new BabyScoreStrategy(child);
        KidScoreStrategy kidScoreStrategy = new KidScoreStrategy(child);
        TeenScoreStrategy teenScoreStrategy = new TeenScoreStrategy(child);
        YoungAdultScoreStrategy youngAdultScoreStrategy = new YoungAdultScoreStrategy(child);
    }
}
