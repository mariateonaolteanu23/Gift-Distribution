package common;

import enums.Cities;
import gifts.Gift;
import receiver.Child;

import java.util.List;

public final class DeliveryData {
    private static DeliveryData deliveryData = null;
    private int currentYear;
    private double santaBudget;
    private List<Child> children;
    private List<Gift> gifts;
    private List<Cities> cities;
    private double budgetUnit;
    private String deliveryStrategy;

    private DeliveryData() { }

    /**
     * Creates a delivery database instance
     * @return delivery database
     */
    public static DeliveryData getDeliveryData() {
        if (deliveryData == null) {
            deliveryData = new DeliveryData();
        }
        return deliveryData;
    }

    public static void setDeliveryData(final DeliveryData deliveryData) {
        DeliveryData.deliveryData = deliveryData;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(final int currentYear) {
        this.currentYear = currentYear;
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

    public double getBudgetUnit() {
        return budgetUnit;
    }

    public void setBudgetUnit(final double budgetUnit) {
        this.budgetUnit = budgetUnit;
    }

    public String getDeliveryStrategy() {
        return deliveryStrategy;
    }

    public void setDeliveryStrategy(final String deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }
}
