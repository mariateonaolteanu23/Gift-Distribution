package callbacks.update;

import common.DeliveryData;
import receiver.Child;

import java.util.List;

public final class ChildrenGiftHistoryEraser implements AnnualUpdaterCallBack {
    /**
     * Erases all gifts received last year (Christmas) by all children
     */
    @Override
    public void update() {
        //  get list of children
        List<Child> children = DeliveryData.getDeliveryData().getChildren();

        //  clear list of received gifts
        for (Child child : children) {
            child.getReceivedGifts().clear();
        }
    }
}
