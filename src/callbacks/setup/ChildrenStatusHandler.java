package callbacks.setup;

import common.DeliveryData;
import enums.Status;
import receiver.Child;

public final class ChildrenStatusHandler implements AnnualSetUpCallBack {

    /**
     * Sets (updates) every child's status
     * Deletes all young adults from delivery database
     */
    @Override
    public void set() {
        //  update every child's status
        for (Child child : DeliveryData.getDeliveryData().getChildren()) {
            child.updateStatus();
        }

        //  delete young adults from children list
        DeliveryData.getDeliveryData().getChildren().
                removeIf(child -> child.getStatusType().equals(Status.YOUNG_ADULT));
    }
}
