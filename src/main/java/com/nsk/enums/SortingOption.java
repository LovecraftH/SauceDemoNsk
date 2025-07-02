package com.nsk.enums;

/**
 * Опции сортировки товаров на странице Products.
 */
public enum SortingOption {
    NAME_A_TO_Z("Name (A to Z)"),
    NAME_Z_TO_A("Name (Z to A)"),
    PRICE_LOW_TO_HIGH("Price (low to high)"),
    PRICE_HIGH_TO_LOW("Price (high to low)");

    private final String displayName;

    SortingOption(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
