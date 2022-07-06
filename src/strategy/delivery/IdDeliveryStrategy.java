package strategy.delivery;

import common.DeliveryData;
import receiver.Child;
import utils.Utils;

public final class IdDeliveryStrategy extends DeliveryStrategy {

    public IdDeliveryStrategy(final String strategy) {
        super(strategy);
    }

    /**
     * Executes id delivery strategy
     * Delivers gifts based on children's id order
     */
    public void executeDeliveryStrategy() {
        //  deliver gifts in the specified order
        for (Child child : DeliveryData.getDeliveryData().getChildren()) {
            Utils.standardGiftDelivery(child);
        }
    }
}
