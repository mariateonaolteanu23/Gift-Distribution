package observers;

import common.DeliveryData;
import enums.Elf;
import receiver.Child;
import utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

public final class YellowElfObserver implements Observer {
    /**
     * Handles children that didn't receive any gifts after the delivery
     */
    @Override
    public void update() {
        //  get a
        List<Child> childrenWithNoGifts = DeliveryData.getDeliveryData().getChildren()
                .stream()
                .filter(child -> child.getReceivedGifts()
                        .size() == 0 && child.getElf() == Elf.YELLOW)
                .collect(Collectors.toList());

        //  deliver gift to selected children
        for (Child child : childrenWithNoGifts) {
            Utils.yellowGiftDelivery(child);
        }
    }
}
