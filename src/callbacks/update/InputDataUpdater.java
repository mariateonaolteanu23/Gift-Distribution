package callbacks.update;

import common.Constants;
import common.DeliveryData;
import fileio.input.AnnualChangeInputData;
import fileio.input.ChildUpdateInputData;
import gifts.Gift;
import receiver.Child;
import utils.Utils;

import java.util.List;

public final class InputDataUpdater implements AnnualUpdaterCallBack {
    private List<AnnualChangeInputData> annualChangeInputDataList;

    public InputDataUpdater(final List<AnnualChangeInputData> annualChangeInputDataList) {
        this.annualChangeInputDataList = annualChangeInputDataList;
    }

    /**
     * Updates the current delivery data based on the corresponding annual change
     */
    @Override
    public void update() {
        //  get annual change for the current year
        AnnualChangeInputData annualChange = annualChangeInputDataList.
                get(DeliveryData.getDeliveryData().getCurrentYear());

        //  update data using the specified changes
        santaBudgetUpdater(annualChange.getNewSantaBudget());
        childrenListUpdater(annualChange.getChildrenUpdates());
        childrenListAdder(annualChange.getNewChildren());
        giftsListAdder(annualChange.getNewGifts());
        deliveryStrategyUpdater(annualChange.getStrategy());
    }

    /**
     * Updates children's data based on the specified list of updates
     * @param childrenUpdates -> list of children updates
     */
    private static void childrenListUpdater(final List<ChildUpdateInputData> childrenUpdates) {
        //  check if there are any children updates this year
        if (childrenUpdates != null) {
            //  iterate through all given updates
            for (ChildUpdateInputData childUpdate : childrenUpdates) {
                //  search child by id to update their info
                for (Child child : DeliveryData.getDeliveryData().getChildren()) {

                    //  found child with specified id
                    //  update child's info
                    if (child.getId() == childUpdate.getId()) {
                        //  add new nice score to list
                        if (childUpdate.getNiceScore() != Constants.NULL_NICE_SCORE) {
                            child.getNiceScoreHistory().add(childUpdate.getNiceScore());
                        }

                        //  update gifts preferences
                        Utils.updateGiftsPreferences(child, childUpdate.getGiftsPreferences());

                        //  update child's elf
                        child.setElf(childUpdate.getNewElf());
                    }
                }
            }
        }
    }

    /**
     * Adds new children to the list of children in the delivery database
     * @param newChildren -> list of new children
     */
    private static void childrenListAdder(final List<Child> newChildren) {
        List<Child> children = DeliveryData.getDeliveryData().getChildren();
        children.addAll(newChildren);
    }

    /**
     * Adds new gifts to the list of gifts in the delivery database
     * @param newGifts -> list of new gifts
     */
    private static void giftsListAdder(final List<Gift> newGifts) {
        List<Gift> gifts = DeliveryData.getDeliveryData().getGifts();
        gifts.addAll(newGifts);
    }

    /**
     * Sets a new santa budget
     * @param newSantaBudget -> new santa budget
     */
    private static void santaBudgetUpdater(final Double newSantaBudget) {
        DeliveryData.getDeliveryData().setSantaBudget(newSantaBudget);
    }

    private static void deliveryStrategyUpdater(final String newDeliveryStrategy) {
        DeliveryData.getDeliveryData().setDeliveryStrategy(newDeliveryStrategy);
    }
}
