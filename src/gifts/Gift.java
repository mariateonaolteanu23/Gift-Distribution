package gifts;

import enums.Category;

public final class Gift {
    private String productName;
    private double price;
    private Category category;
    private int quantity;

    public Gift(final String productName, final double price, final Category category,
                final int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
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

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    /**
     * Checks if gift passes filters
     * @param filterCategory -> category filter
     * @return true if gift meets conditions, false otherwise
     */
    public boolean checkGiftFilters(final Category filterCategory) {
        return this.category == filterCategory;
    }

    /**
     * Checks if gift is in stock
     * @return true if gift is in stock, false otherwise
     */
    public boolean isInStock() {
        return this.quantity > 0;
    }
}
