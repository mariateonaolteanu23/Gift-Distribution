package callbacks.setup;

import common.Constants;
import common.DeliveryData;
import enums.Elf;
import receiver.Child;

public final class ChildBudgetHandler implements AnnualSetUpCallBack {
    /**
     * Computes and sets the assigned budget for each child
     */
    @Override
    public void set() {
        for (Child child : DeliveryData.getDeliveryData().getChildren()) {
            //  compute assigned budget
            double assignedBudget = DeliveryData.getDeliveryData().getBudgetUnit()
                    * child.getNiceScore();

            // handle pink elf change
            if (child.getElf() == Elf.PINK) {
                assignedBudget += assignedBudget * Constants.BUDGET_ADD_SUB / Constants.PERCENT;
            }

            // handle black elf change
            if (child.getElf() == Elf.BLACK) {
                assignedBudget -= assignedBudget * Constants.BUDGET_ADD_SUB / Constants.PERCENT;
            }

            //  store budget
            child.setAssignedBudget(assignedBudget);
        }
    }
}
