package strategy.delivery;

import enums.Cities;
import enums.EnumUtils;
import receiver.Child;
import utils.Utils;

import java.util.List;

public final class NiceScoreCityDeliveryStrategy extends DeliveryStrategy {
    public NiceScoreCityDeliveryStrategy(final String strategy) {
        super(strategy);
    }

    /**
     * Executes niceScoreCity delivery strategy
     * Delivers gifts based on cities average score ranking
     */
    public void executeDeliveryStrategy() {
        //  get sorted list of cities strings by average nice score
        List<String> sortedCities = Utils.sortCitiesByNiceScore();

        //  iterate through sorted list of cities strings
        for (String stringCity : sortedCities) {

            //  get city enum based on string
            Cities city = EnumUtils.stringToCity(stringCity.charAt(0)
                    + stringCity.substring(1).toLowerCase());

            //  get all children that live in the specified city
            List<Child> childrenByCity = Utils.getFilteredChildren(city);

            //  deliver gifts in the specified order
            for (Child child : childrenByCity) {
                Utils.standardGiftDelivery(child);
            }
        }
    }
}
