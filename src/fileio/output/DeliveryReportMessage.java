package fileio.output;

import enums.Category;
import enums.Cities;
import gifts.DeliveredGift;
import receiver.Child;

import java.util.ArrayList;
import java.util.List;

public final class DeliveryReportMessage {
    private final int id;
    private final String lastName;
    private final String firstName;
    private Cities city;
    private int age;
    private List<Category> giftsPreferences;
    private double averageScore;
    private List<Double> niceScoreHistory;
    private double assignedBudget;
    private List<DeliveredGift> receivedGifts;

    public DeliveryReportMessage(final int id, final String lastName, final String firstName,
                                 final int age, final Cities city, final double averageScore,
                                 final List<Category> giftsPreferences,
                                 final List<Double> niceScoreHistory,
                                 final List<DeliveredGift> receivedGifts,
                                 final double assignedBudget) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.averageScore = averageScore;
        this.giftsPreferences = giftsPreferences;
        this.niceScoreHistory = niceScoreHistory;
        this.receivedGifts = receivedGifts;
        this.assignedBudget = assignedBudget;
    }

    public DeliveryReportMessage(final Child child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.age = child.getAge();
        this.city = child.getCity();
        this.averageScore = child.getNiceScore();
        this.giftsPreferences = new ArrayList<>(child.getGiftsPreference());
        this.niceScoreHistory = new ArrayList<>(child.getNiceScoreHistory());
        this.receivedGifts = new ArrayList<>(child.getReceivedGifts());
        this.assignedBudget = child.getAssignedBudget();
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public Cities getCity() {
        return city;
    }


    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }


    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public List<DeliveredGift> getReceivedGifts() {
        return receivedGifts;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public double getAssignedBudget() {
        return assignedBudget;
    }
}
