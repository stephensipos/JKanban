package com.stephensipos.jkanban.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class WelcomePane extends BorderPane {
    public WelcomePane(Scene scene) throws IOException {
        super();
        initializeFromFxml(this, scene);
    }

    public void close(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void about(ActionEvent actionEvent) {
    }

    public void createBoard(ActionEvent actionEvent) throws IOException {
        var createBoard = new CreateBoardDialog();
        createBoard.showAndWait();
    }
}
