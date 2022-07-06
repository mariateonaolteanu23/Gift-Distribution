package gifts;

import enums.Category;

public final class DeliveredGift {
    private String productName;
    private double price;
    private Category category;

    public DeliveredGift(final Gift gift) {
        this.productName = gift.getProductName();
        this.price = gift.getPrice();
        this.category = gift.getCategory();
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
