package com.stephensipos.jkanban.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateBoard extends BorderPane implements ICustomComponent {
    private Stage stage = new Stage();

    @FXML private TextField boardName;
    @FXML private Button okButton;

    public CreateBoard() throws IOException {
        stage.initModality(Modality.APPLICATION_MODAL);
        initialize(stage, this);
    }

    public void initializeControls() {
        this.initializeOkButton();
    }

    private void initializeOkButton() {
        this.boardName.textProperty().addListener((obj, oldText, newText) -> {
            this.validate(newText);
        });

        this.validate(this.boardName.getText());
    }

    public void cancel(ActionEvent event) {
        stage.close();
    }

    public void ok(ActionEvent event) {
        System.out.println(this.boardName.getText());
        System.out.println(this.getScene().getWindow().getClass());
    }

    private void validate(String boardNameText) {
        this.okButton.setDisable(boardNameText.isEmpty());
    }

    public void showAndWait() {
        stage.showAndWait();
    }
}
