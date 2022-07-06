package factory;

import common.Constants;
import strategy.delivery.DeliveryStrategy;

import java.lang.reflect.InvocationTargetException;

public final class DeliveryStrategyFactory {
    private DeliveryStrategyFactory() { }

    //  Gets class package and class name
    //  (e.g. strategy.delivery.NiceScoreDeliveryStrategy)
    private static String getStrategyType(final String strategyType) {
        return Constants.DELIVERY_STRATEGY_PACKAGE
                + strategyType.substring(0, 1).toUpperCase()
                + strategyType.substring(1)
                + Constants.DELIVERY_STRATEGY;
    }

    /**
     * Creates a new DeliveryStrategy instance based on child's status
     * (e.g. IdDeliveryStrategy)
     * @param deliveryType -> constructor parameter
     * @return new instance of DeliveryStrategy class
     */
    public static DeliveryStrategy createDeliveryStrategy(final String deliveryType) {
        try {
            //  Constructor's parameter type
            var arguments = new Class[]{String.class};
            //  Use given string to determine class name
            //  Call constructor of the class with the given argument
            //  Returns a new DeliveryStrategy object
            return (DeliveryStrategy) Class.forName(getStrategyType(deliveryType))
                    .getDeclaredConstructor(arguments).newInstance(deliveryType);
            //  Catch exceptions
        } catch (ClassNotFoundException | InvocationTargetException
                | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
