package observers;

import common.DeliveryData;
import fileio.output.DeliveryReportMessage;
import fileio.output.Writer;
import receiver.Child;

import java.util.ArrayList;
import java.util.List;

public final class DeliveryDataAggregator implements Observer {
    private Writer dataWriter;

    public DeliveryDataAggregator(final Writer dataWriter) {
        this.dataWriter = dataWriter;
    }

    /**
     * Coverts (to JSONObject) and stores a list of delivery report messages
     * after a delivery has been executed
     */
    @Override
    public void update() {
        List<DeliveryReportMessage> deliveryReportMessage = new ArrayList<>();

        //  get delivery report for every child
        for (Child child : DeliveryData.getDeliveryData().getChildren()) {
            deliveryReportMessage.add(new DeliveryReportMessage(child));
        }

        //  store and convert list
        dataWriter.writeFile(deliveryReportMessage);
    }
}
