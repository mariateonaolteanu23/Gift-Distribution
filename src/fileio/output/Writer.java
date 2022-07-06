package fileio.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public final class Writer {
    private JSONArray allDeliveryReports = new JSONArray();

    public Writer() {
    }

    /**
     * Converts a list of delivery reports to a JSONObject
     * @param deliveryReport -> list of reports of the last executed delivery
     */
    public void writeFile(final List<DeliveryReportMessage> deliveryReport) {
        JSONObject object = new JSONObject();
        object.put("children", deliveryReport);
        allDeliveryReports.add(object);
    }

    /**
     * Writes output data to output file
     * @param file -> output file
     */
    public void closeJSON(final FileWriter file) {
        try {
            JSONObject object = new JSONObject();
            object.put("annualChildren", allDeliveryReports);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            file.write(json);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
