package callbacks.update;

import common.DeliveryData;

public final class CurrentDateUpdater implements AnnualUpdaterCallBack {
    /**
     * Updates current delivery year
     */
    @Override
    public void update() {
        //  increment year by 1
        DeliveryData.getDeliveryData().
                setCurrentYear(DeliveryData.getDeliveryData().getCurrentYear() + 1);
    }
}
