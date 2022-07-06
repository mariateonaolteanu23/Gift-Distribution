package fileio.input;

import enums.Cities;
import gifts.Gift;
import receiver.Child;

import java.util.List;

public final class Input {
    private int numberOfYears;
    private double santaBudget;
    private List<Child> children;
    private List<Gift> gifts;
    private List<Cities> cities;
    private List<AnnualChangeInputData> annualChanges;

    public Input(final int numberOfYears, final double santaBudget,
                 final List<Child> children, final List<Gift> gifts,
                 final List<Cities> cities, final List<AnnualChangeInputData> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.children = children;
        this.gifts = gifts;
        this.cities = cities;
        this.annualChanges = annualChanges;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(final List<Gift> gifts) {
        this.gifts = gifts;
    }

    public List<Cities> getCities() {
        return cities;
    }

    public void setCities(final List<Cities> cities) {
        this.cities = cities;
    }

    public List<AnnualChangeInputData> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(final List<AnnualChangeInputData> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
