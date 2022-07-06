package fileio;

import common.Constants;
import enums.Cities;
import enums.EnumUtils;
import fileio.input.AnnualChangeInputData;
import fileio.input.ChildUpdateInputData;
import gifts.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import receiver.Child;

import java.util.ArrayList;
import java.util.List;

public final class ParseUtils {
    private ParseUtils() { }

    /**
     * Transforms a JSONArray into a list of children
     * @param jsonChildren -> JSONArray
     * @return list of children
     */
    public static List<Child> parseChildrenList(final JSONArray jsonChildren) {
        List<Child> children = new ArrayList<>();

        if (jsonChildren != null) {
            for (Object jsonChild : jsonChildren) {
                children.add(new Child(
                        Integer.parseInt(((Long) ((JSONObject) jsonChild).
                                get(Constants.ID)).toString()),
                        (String) ((JSONObject) jsonChild).get(Constants.LAST_NAME),
                        (String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME),
                        Integer.parseInt(((Long) ((JSONObject) jsonChild).
                                get(Constants.AGE)).toString()),
                        EnumUtils.stringToCity((String) ((JSONObject) jsonChild).
                                get(Constants.CITY)),
                        Double.parseDouble(((Long) ((JSONObject) jsonChild).
                                getOrDefault(Constants.NICE_SCORE, (double) 0)).toString()),
                        EnumUtils.jsonArrayToCategories((JSONArray) ((JSONObject) jsonChild).
                                get(Constants.PREFERENCES)),
                        Integer.parseInt(((Long) ((JSONObject) jsonChild).
                                get(Constants.NICE_SCORE_BONUS)).toString()),
                        EnumUtils.stringToElf((String) ((JSONObject) jsonChild).
                                get(Constants.ELF))

                ));
            }
        }

        return children;
    }

    /**
     * Transforms a JSONArray into a list of gifts
     * @param jsonGifts -> JSONArray
     * @return a list of gifts
     */
    public static List<Gift> parseGiftsList(final JSONArray jsonGifts) {
        List<Gift> gifts = new ArrayList<>();
        if (jsonGifts != null) {
            for (Object jsonGift : jsonGifts) {
                gifts.add(new Gift(
                        (String) ((JSONObject) jsonGift).get(Constants.PRODUCT_NAME),
                        Double.parseDouble(((Long) ((JSONObject) jsonGift).
                                get(Constants.PRICE)).toString()),
                        EnumUtils.stringToCategory((String) ((JSONObject) jsonGift).
                                get(Constants.CATEGORY)),
                        Integer.parseInt(((Long) ((JSONObject) jsonGift).
                                get(Constants.QUANTITY)).toString())
                ));
            }
        }

        return gifts;
    }

    /**
     * Transforms a JSONArray into a list of Cities Enums
     * @param jsonCities -> JSONArray
     * @return a list of Cities Enums
     */
    public static List<Cities> parseCitiesList(final JSONArray jsonCities) {
        List<Cities> cities = new ArrayList<>();

        if (jsonCities != null) {
            cities = EnumUtils.jsonArrayToCities(jsonCities);
        }

        return cities;
    }

    /**
     * Parses nice score value
     * @param jsonUpdate -> JSONObject
     * @return a double value
     */
    private static double niceScoreHandler(final JSONObject jsonUpdate) {
        //  handle null case
        if (jsonUpdate.get(Constants.NICE_SCORE) == null) {
            return Constants.NULL_NICE_SCORE;
        }
        //  get nice score
        return Double.parseDouble(((Long) jsonUpdate.
                getOrDefault(Constants.NICE_SCORE, 0)).toString());
    }

    /**
     * Transforms a JSONArray into a list of children updates
     * @param jsonUpdates -> JSONArray
     * @return a list of children updates
     */
    public static List<ChildUpdateInputData> parseChildrenUpdates(final JSONArray jsonUpdates) {
        List<ChildUpdateInputData> updates = new ArrayList<>();
        if (jsonUpdates != null) {
            for (Object jsonUpdate : jsonUpdates) {
                updates.add(new ChildUpdateInputData(
                        Integer.parseInt(((Long) ((JSONObject) jsonUpdate).
                                get(Constants.ID)).toString()),
                        niceScoreHandler((JSONObject) jsonUpdate),
                        EnumUtils.jsonArrayToCategories((JSONArray) ((JSONObject) jsonUpdate).
                                get(Constants.PREFERENCES)),
                        EnumUtils.stringToElf((String) ((JSONObject) jsonUpdate).
                                get(Constants.ELF))
                ));
            }
        }
        return updates;
    }

    /**
     * Transforms a JSONArray into a list of annual changes
     * @param jsonChanges -> JSONArray
     * @return a list of annual changes
     */
    public static List<AnnualChangeInputData> parseAnnualChanges(final JSONArray jsonChanges) {
        List<AnnualChangeInputData> annualChangeInputData = new ArrayList<>();
        if (jsonChanges != null) {
            for (Object jsonChange : jsonChanges) {
                annualChangeInputData.add(new AnnualChangeInputData(
                        Double.parseDouble(((Long) ((JSONObject) jsonChange).
                                get(Constants.NEW_BUDGET)).toString()),

                        ParseUtils.parseGiftsList((JSONArray) ((JSONObject) jsonChange).
                                get(Constants.NEW_GIFTS)),

                        ParseUtils.parseChildrenList((JSONArray) ((JSONObject) jsonChange).
                                get(Constants.NEW_CHILDREN)),

                        ParseUtils.parseChildrenUpdates((JSONArray) ((JSONObject) jsonChange).
                                get(Constants.CHILDREN_UPDATES)),
                        (String) ((JSONObject)  jsonChange).get(Constants.STRATEGY)

                ));
            }
        }
        return annualChangeInputData;
    }
}
