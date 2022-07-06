package strategy.delivery;

public abstract class DeliveryStrategy {
    private String strategy;

    public DeliveryStrategy(final String strategy) {
        this.strategy = strategy;
    }

    /**
     * Gets delivery's strategy type
     * @return a string
     */
    public String getStrategy() {
        return strategy;
    }

    /**
     * Delivers / distributes gifts to all children in a particular order
     */
    public abstract void executeDeliveryStrategy();
}
