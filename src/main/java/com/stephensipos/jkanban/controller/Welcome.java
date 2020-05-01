package com.stephensipos.jkanban.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class Welcome extends BorderPane {
    public Welcome(Scene scene) throws IOException {
        initializeFromFxml(this, scene);
    }

    public void close(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void about(ActionEvent actionEvent) {
    }

    public void createBoard(ActionEvent actionEvent) throws IOException {
        var createBoard = new CreateBoard();
        createBoard.showAndWait();
    }
}
