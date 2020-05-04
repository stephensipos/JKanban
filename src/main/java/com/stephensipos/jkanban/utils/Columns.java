package com.stephensipos.jkanban.utils;

public class Columns {
    private static String[] supportedColumns = new String[] {"To-do", "Do today", "In progress", "Done"};

    public static String[] getSupportedColumns() {
        return supportedColumns.clone();
    }

}
