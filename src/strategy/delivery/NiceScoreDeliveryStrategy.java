package strategy.delivery;

import common.DeliveryData;
import receiver.Child;
import utils.Utils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class NiceScoreDeliveryStrategy extends DeliveryStrategy {
    public NiceScoreDeliveryStrategy(final String strategy) {
        super(strategy);
    }

    /**
     * Executes niceScore delivery strategy
     * Delivers gifts based on children's nice score order
     * (from the biggest nice score to the lowest)
     */
    public void executeDeliveryStrategy() {
        //  get list of children sorted by nice score in descending order
        List<Child> sortedChildren = DeliveryData.getDeliveryData().getChildren()
                .stream()
                .sorted(Comparator.comparing(Child::getNiceScore).reversed())
                .collect(Collectors.toList());

        //  deliver gifts in specified order
        for (Child child : sortedChildren) {
            Utils.standardGiftDelivery(child);
        }
    }
}
