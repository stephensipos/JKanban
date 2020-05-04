package com.stephensipos.jkanban.controller;

import javafx.event.Event;
import javafx.event.EventType;

public class ColumnChangedEvent extends Event {
    public static final EventType<ColumnChangedEvent> COLUMN_CHANGED_EVENT_TYPE = new EventType(ANY);

    public ColumnChangedEvent() {
        super(COLUMN_CHANGED_EVENT_TYPE);
    }
}
