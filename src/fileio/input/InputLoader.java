package fileio.input;

import common.Constants;
import enums.Cities;
import fileio.ParseUtils;
import gifts.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import receiver.Child;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class InputLoader {
    public InputLoader() {
    }

    /**
     * Reads and loads input from input file
     * @param path -> path of the input file
     * @return input
     */
    public Input readInputData(final String path) {
        JSONParser jsonParser = new JSONParser();

        int numberOfYears = 0;
        double santaBudget = 0;
        List<Child> children = new ArrayList<>();
        List<Gift> gifts = new ArrayList<>();
        List<Cities> cities = new ArrayList<>();
        List<AnnualChangeInputData> annualChanges = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(path));

            //  parse number of years
            numberOfYears = Integer.parseInt(((Long) jsonObject.get(Constants.YEARS)).toString());

            //  parse santa's budget
            santaBudget = Double.parseDouble(((Long) jsonObject.get(Constants.BUDGET)).toString());

            //  parse initial data
            JSONObject data = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);

            //  parse list of children
            JSONArray jsonChildren = (JSONArray) data.get(Constants.CHILDREN);
            children = ParseUtils.parseChildrenList(jsonChildren);

            //  parse list of gifts
            JSONArray jsonGifts = (JSONArray) data.get(Constants.GIFT_LIST);
            gifts = ParseUtils.parseGiftsList(jsonGifts);

            // parse list of cities
            JSONArray jsonCities = (JSONArray) data.get(Constants.CITY);
            cities = ParseUtils.parseCitiesList(jsonCities);

            //  parse list of annual changes
            JSONArray jsonChanges = (JSONArray) jsonObject.get(Constants.CHANGES);
            annualChanges = ParseUtils.parseAnnualChanges(jsonChanges);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return new Input(numberOfYears, santaBudget, children, gifts, cities, annualChanges);
    }
}
