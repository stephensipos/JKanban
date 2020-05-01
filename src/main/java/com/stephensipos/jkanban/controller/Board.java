package com.stephensipos.jkanban.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class Board extends BorderPane {
    @FXML
    private Label boardName;

    public Board(String name, Scene scene) throws IOException {
        initializeFromFxml(this, scene);
        this.boardName.setText(name);
    }

    public void close(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void about(ActionEvent actionEvent) {
    }
}
