package com.stephensipos.jkanban.utils;

public class Categories {
    private static String[] supportedCategories = new String[] {"Work", "School", "Family"};

    public static String[] getSupportedCategories() {
        return supportedCategories.clone();
    }

    public static String getDefaultCategory() {
        return supportedCategories[0];
    }
}
