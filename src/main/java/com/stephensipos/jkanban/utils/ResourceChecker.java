package com.stephensipos.jkanban.utils;

public class ResourceChecker {
    public static boolean resourceExists(String resourceName) {
        return ResourceChecker.class.getResource(resourceName) != null;
    }

}
