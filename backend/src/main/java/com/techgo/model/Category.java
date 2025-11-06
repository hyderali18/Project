package com.techgo.model;

/**
 * Enum representing supported gadget categories in the TechGo application.
 * Each category corresponds to a type of electronic gadget that can be compared.
 */
public enum Category {
    MOBILES("mobiles", "Mobile Phones"),
    LAPTOPS("laptops", "Laptops"),
    TABLETS("tablets", "Tablets"),
    EARPHONES("earphones", "Earphones & Headphones"),
    SPEAKERS("speakers", "Speakers");

    private final String value;
    private final String displayName;

    Category(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Get Category enum from string value
     * @param value the string representation
     * @return Category enum or null if not found
     */
    public static Category fromValue(String value) {
        for (Category category : Category.values()) {
            if (category.value.equalsIgnoreCase(value)) {
                return category;
            }
        }
        return null;
    }
}