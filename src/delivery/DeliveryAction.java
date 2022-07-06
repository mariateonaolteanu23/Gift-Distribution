package delivery;

import callbacks.setup.AnnualSetUpCallBack;
import common.DeliveryData;
import factory.DeliveryStrategyFactory;
import observable.Observable;
import observers.Observer;

import strategy.delivery.DeliveryStrategy;

import java.util.ArrayList;
import java.util.List;

public final class DeliveryAction implements Observable {

    private final List<AnnualSetUpCallBack> annualSetUpCallBacks = new ArrayList<>();
    private List<Observer> deliveryActionObservers = new ArrayList<>();

    public DeliveryAction() { }

    /**
     * Adds an annual set-up callback to the list of callbacks
     * @param annualSetUpCallBack -> new annual set-up callback
     */
    public void registerAnnualSetUp(final AnnualSetUpCallBack annualSetUpCallBack) {
        annualSetUpCallBacks.add(annualSetUpCallBack);
    }

    /**
     * Executes an annual delivery
     * An annual delivery entails that all children in delivery database receive Christmas gifts
     */
    public void executeAnnualDelivery() {
        //  update and set data necessary for new delivery
        doAnnualSetUp();

        //  deliver gifts to all children in children's list
        doDelivery();

        //  all deliveries are done
        notifyAllObservers();
    }

    /**
     * Executes all registered annual set-ups
     */
    void doAnnualSetUp() {
        for (AnnualSetUpCallBack annualSetUp : annualSetUpCallBacks) {
            annualSetUp.set();
        }
    }

    /**
     * Delivers gifts to all children in the delivery database
     */
    private static void doDelivery() {
        //  deliver gifts to children

        //  get/create delivery strategy
        DeliveryStrategy deliveryStrategy = DeliveryStrategyFactory
                .createDeliveryStrategy(DeliveryData.getDeliveryData().getDeliveryStrategy());
        assert deliveryStrategy != null;

        //  execute delivery strategy
        deliveryStrategy.executeDeliveryStrategy();
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observer : deliveryActionObservers) {
            observer.update();
        }
    }

    @Override
    public void addObserver(final Observer observer) {
        deliveryActionObservers.add(observer);
    }

    @Override
    public void deleteObserver(final Observer observer) {
        deliveryActionObservers.remove(observer);
    }
}
