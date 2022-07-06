package observable;

import observers.Observer;

public interface Observable {
    /**
     * Notifies all observers of a change in the DeliveryAction class
     */
    void notifyAllObservers();

    /**
     * Adds a new observer to the list of observers
     * @param observer -> new observer
     */
    void addObserver(Observer observer);

    /**
     * Removes given observer from to the list of observers
     * @param observer
     */
    void deleteObserver(Observer observer);
}
