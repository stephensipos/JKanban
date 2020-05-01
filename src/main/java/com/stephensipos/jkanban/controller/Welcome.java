package com.stephensipos.jkanban.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Welcome extends BorderPane implements ICustomComponent {
    private Stage stage = new Stage();

    public Welcome() throws IOException {
        this.initialize(stage, this);
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
