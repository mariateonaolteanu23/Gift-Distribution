package callbacks.update;

import common.DeliveryData;
import receiver.Child;

import java.util.List;

public final class ChildrenAgeUpdater implements AnnualUpdaterCallBack {
    /**
     * Updates the age of all children list in the delivery database
     */
    @Override
    public void update() {
        //  get list of children
        List<Child> children = DeliveryData.getDeliveryData().getChildren();

        //  update age of every child
        for (Child child : children) {
            //  increment age by 1
            child.setAge(child.getAge() + 1);
        }
    }
}
