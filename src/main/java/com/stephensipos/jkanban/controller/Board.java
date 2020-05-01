package com.stephensipos.jkanban.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Board extends BorderPane implements ICustomComponent {
    private Stage stage = new Stage();
    private String name;

    @FXML
    private Label boardName;

    public Board(String name) throws IOException {
        this.name = name;
        this.boardName.setText(name);

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
