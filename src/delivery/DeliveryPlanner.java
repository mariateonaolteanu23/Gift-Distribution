package delivery;

import callbacks.update.AnnualUpdaterCallBack;
import common.DeliveryData;

import java.util.ArrayList;
import java.util.List;

public final class DeliveryPlanner  {
    private final int numberOfYears;
    private List<AnnualUpdaterCallBack> annualCallBacks = new ArrayList<>();

    public DeliveryPlanner(final int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /**
     * Adds an annual updater callback to the list of callbacks
     * @param annualUpdaterCallBack -> new annual updater callback
     */
    public void registerAnnualUpdates(final AnnualUpdaterCallBack annualUpdaterCallBack) {
       annualCallBacks.add(annualUpdaterCallBack);
   }

    /**
     * Checks if all annual planned deliveries were executed
     * @return true if all deliveries were executed, false otherwise
     */
   public boolean deliveryPlanIsDone() {
        return numberOfYears == DeliveryData.getDeliveryData().getCurrentYear();
   }

    /**
     * Executes all registered annual updates after a year has passed
     */
   public void oneYearHasPassed() {
       //  iterate through all registered updater callbacks
       for (AnnualUpdaterCallBack annualCallBack : annualCallBacks) {
           //  call update method
           annualCallBack.update();
       }
   }

    public int getNumberOfYears() {
        return numberOfYears;
    }
}
