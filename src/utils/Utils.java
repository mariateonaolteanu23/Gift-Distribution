package utils;

import common.DeliveryData;
import enums.Category;
import enums.Cities;
import gifts.DeliveredGift;
import gifts.Gift;
import receiver.Child;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class Utils {

    private Utils() { }

    /**
     * Filters a list of gifts by category and sorts it by price (ascending order)
     * @param category -> category filter
     * @return list of filtered and sorted gifts
     */
    public static List<Gift> getFilteredGifts(final Category category) {
        //  get the list of gifts
        List<Gift> gifts = DeliveryData.getDeliveryData().getGifts();

        //  filter gifts by category and sort in ascending them by price
        Predicate<Gift> giftPredicate = gift -> gift.checkGiftFilters(category);
        return gifts.stream().filter(giftPredicate).
                sorted(Comparator.comparing(Gift::getPrice)).collect(Collectors.toList());
    }

    /**
     * Gets the cheapest gift in stock from a list of sorted gifts by price
     * @param sortedGifts sorted gifts list
     * @return gift
     */
    public static Gift getCheapestGift(final List<Gift> sortedGifts) {
        //  iterate through sorted gifts
        for (Gift gift : sortedGifts) {
            //  found the cheapest gift in stock
            if (gift.isInStock()) {
                return gift;
            }
        }

        //  there aren't any gifts
        return null;
    }

    /**
     * Filters a list of children by the city they live in
     * @param city -> category filter
     * @return list of filtered children
     */
    public static List<Child> getFilteredChildren(final Cities city) {
        //  get the list of gifts
        List<Child> children = DeliveryData.getDeliveryData().getChildren();

        //  filter children by city
        Predicate<Child> childPredicate = child -> child.checkChildFilters(city);

        return children.stream().filter(childPredicate).collect(Collectors.toList());
    }

    /**
     * Updates a child's gifts preferences list
     * Adds new gifts preferences to original list
     * @param child -> child to perform action on
     * @param newPreferences -> list of new preferences
     */
    public static void updateGiftsPreferences(final Child child,
                                              final List<Category> newPreferences) {
        //  check if there are gifts preferences updates
        if (newPreferences != null) {

            //  remove duplicates from the new given list of gifts preferences
            LinkedHashSet<Category> linkedHashSet = new LinkedHashSet<>(newPreferences);

            //  check for common gifts preferences between the original and the new preferences
            List<Category> auxList = new ArrayList<>(child.getGiftsPreference());
            //  store common gifts preferences
            auxList.retainAll(linkedHashSet);

            //  remove common gifts preferences from original list
            for (Category category : auxList) {
                child.getGiftsPreference().remove(category);
            }

            //  update gifts preferences
            child.getGiftsPreference().addAll(0, linkedHashSet);
        }
    }

    /**
     * Delivers gifts to a child based on the specified requirements
     * @param child given child
     */
    public static void standardGiftDelivery(final Child child) {
        //  get child's assigned budget
        double maxBudget = child.getAssignedBudget();

        //  search gifts based on child's preferences
        for (Category category : child.getGiftsPreference()) {

            //  get the cheapest gift in stock in the specified category
            Gift gift = Utils.getCheapestGift(Utils.getFilteredGifts(category));

            if (gift != null) {
                // gift is within assigned budget
                if (maxBudget >= gift.getPrice()) {
                    //  add gift to child's list of received gifts
                    child.getReceivedGifts().add(new DeliveredGift(gift));

                    // update quantity
                    gift.setQuantity(gift.getQuantity() - 1);

                    //  update assigned budget
                    maxBudget -= gift.getPrice();
                }
            }
        }
    }

    /**
     * Delivers a single gift to a child without considering the assigned budget
     * @param child given child
     */
    public static void yellowGiftDelivery(final Child child) {
        Category category = child.getGiftsPreference().get(0);
        //  get list of gifts in the specified category
        //  this list of gifts is also sorted in ascending order by price
        List<Gift> gifts = Utils.getFilteredGifts(category);

        //  there are no gifts in the specified category
        if (gifts.isEmpty()) {
            //  can't delivery gift
            return;
        }

        //  get the cheapest gift
        Gift gift = gifts.get(0);
        if (gift.isInStock()) {
            //  add gift to child's list of received gifts
            child.getReceivedGifts().add(new DeliveredGift(gift));

            // update quantity
            gift.setQuantity(gift.getQuantity() - 1);
        }
    }

    /**
     * Sorts cities by the all around average score
     * @return a list of cities sorted in descending order by average score
     */
    public static List<String> sortCitiesByNiceScore() {
        LinkedHashMap<String, Double> niceScoreCities = new LinkedHashMap<>();

        //  map cities by the all around average score

        //  iterate through all children sorted in ascending order by id
        for (Child child : DeliveryData.getDeliveryData().getChildren()) {
            //  check if a city has been already mapped
            double cityScore = niceScoreCities.getOrDefault(child.getCity().toString(), (double) 0);

            // if not
            if (cityScore == 0) {
                //  map city and child's average score
                niceScoreCities.put(child.getCity().toString(), child.getNiceScore());
            } else {
                //  update city's average score
                niceScoreCities.put(child.getCity().toString(),
                        (cityScore + child.getNiceScore()) / 2);
            }
        }

        //  transform map in a list of cities sorted by their average score
        return niceScoreCities.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
