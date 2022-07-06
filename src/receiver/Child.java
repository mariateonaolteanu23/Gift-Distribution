package receiver;

import common.Constants;
import enums.Category;
import enums.Cities;
import enums.Elf;
import enums.Status;
import gifts.DeliveredGift;
import score.NiceScore;

import java.util.ArrayList;
import java.util.List;

public final class Child {
    private final int id;
    private final String lastName;
    private final String firstName;
    private int age;
    private Cities city;
    private List<Category> giftsPreference;
    private NiceScore niceScore;
    private Elf elf;

    private Status statusType;
    private List<Double> niceScoreHistory = new ArrayList<>();
    private List<DeliveredGift> receivedGifts = new ArrayList<>();
    private double assignedBudget;

    public Child(final int id, final String lastName, final String firstName,
                 final int age, final Cities city, final double niceScore,
                 final List<Category> giftsPreference, final int niceScoreBonus, final Elf elf) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.giftsPreference = giftsPreference;
        this.elf = elf;
        //  build nice score
        this.niceScore = new NiceScore.Builder(niceScore).addBonus(niceScoreBonus).build();
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

    public void setAge(final int age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(final Cities city) {
        this.city = city;
    }

    public List<Category> getGiftsPreference() {
        return giftsPreference;
    }

    public void setGiftsPreference(final List<Category> giftsPreference) {
        this.giftsPreference = giftsPreference;
    }

    public Status getStatusType() {
        return statusType;
    }

    public void setStatusType(final Status statusType) {
        this.statusType = statusType;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public List<DeliveredGift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(final List<DeliveredGift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(final double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    /**
     * Gets child's nice score
     * @return child's nice score value
     */
    public double getNiceScore() {
        return niceScore.getNiceScore();
    }

    /**
     * Sets new child's nice score
     * @param niceScore new score value
     */
    public void setNiceScore(final double niceScore) {
        this.niceScore.setNiceScore(niceScore);
    }

    public Elf getElf() {
        return elf;
    }

    public void setElf(final Elf elf) {
        this.elf = elf;
    }

    /***
     * Updates status of child based on age
     */
    public void updateStatus() {
        if (age < Constants.MAX_BABY_AGE) {
           setStatusType(Status.BABY);
        } else if (age < Constants.MAX_KID_AGE) {
            setStatusType(Status.KID);
        } else if (age <= Constants.MAX_TEEN_AGE) {
           setStatusType(Status.TEEN);
        } else {
            setStatusType(Status.YOUNG_ADULT);
        }
    }

    /**
     * Checks if the list of nice scores is empty
     * @return true if niceScoreHistory is empty, false otherwise
     */
    public boolean scoreHistoryIsEmpty() {
        return niceScoreHistory.size() == 0;
    }

    /**
     * Checks if gift passes filters
     * @param cityFilter -> category filter
     * @return true if gift meets conditions, false otherwise
     */
    public boolean checkChildFilters(final Cities cityFilter) {
        return this.city == cityFilter;
    }
}
