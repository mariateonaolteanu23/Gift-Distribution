package callbacks.setup;

import common.DeliveryData;
import receiver.Child;

import java.util.Comparator;

public final class IdSortHandler implements AnnualSetUpCallBack {
    /**
     * Sets (updates) children's id order
     * Ids should always be sorted in ascending order before a delivery
     */
    @Override
    public void set() {
        DeliveryData.getDeliveryData().getChildren().sort(Comparator.comparing(Child::getId));
    }
}
