package com.stephensipos.jkanban.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class ColumnPane extends Pane {
    @FXML private Label title;
    @FXML private VBox contents;

    public ColumnPane(Scene scene, String name) throws IOException {
        super();
        initializeFromFxml(this, scene);
        this.title.setText(name);
    }

    public void addCard(CardPane card) {
        ((Region) card).prefWidthProperty().bind(this.contents.widthProperty());
        this.contents.getChildren().add(card);
    }
}
