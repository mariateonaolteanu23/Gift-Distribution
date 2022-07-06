package fileio.input;

import gifts.Gift;
import receiver.Child;
import java.util.List;

public final class AnnualChangeInputData {
    private double newSantaBudget;
    private List<Gift> newGifts;
    private List<Child> newChildren;
    private List<ChildUpdateInputData> childrenUpdates;
    private String strategy;

    public AnnualChangeInputData(final double newSantaBudget,
                                 final List<Gift> newGifts,
                                 final List<Child> newChildren,
                                 final List<ChildUpdateInputData> childrenUpdates,
                                 final String strategy) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
        this.strategy = strategy;
    }

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public List<Gift> getNewGifts() {
        return newGifts;
    }

    public List<Child> getNewChildren() {
        return newChildren;
    }

    public List<ChildUpdateInputData> getChildrenUpdates() {
        return childrenUpdates;
    }

    public String getStrategy() {
        return strategy;
    }
}
