package callbacks.setup;

import common.DeliveryData;
import receiver.Child;

public final class BudgetUnitHandler implements AnnualSetUpCallBack {

    /**
     * Computes and sets the budget unit
     */
    @Override
    public void set() {
        double sumOfScores = 0;

        //  get the sum of all children's nice scores
        for (Child child : DeliveryData.getDeliveryData().getChildren()) {
            sumOfScores += child.getNiceScore();
        }

        //  compute budget unit
        double budgetUnit = DeliveryData.getDeliveryData().getSantaBudget() /  sumOfScores;

        //  store budget unit in delivery database
        DeliveryData.getDeliveryData().setBudgetUnit(budgetUnit);
    }
}
