package com.stephensipos.jkanban.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class CreateBoardDialog extends BorderPane {

    private Stage stage;
    private Scene scene;

    @FXML private TextField boardName;
    @FXML private Button okButton;

    public CreateBoardDialog() throws IOException {
        super();
        stage = new Stage();
        scene = new Scene(this);

        stage.initModality(Modality.APPLICATION_MODAL);
        initializeFromFxml(this);

        stage.setScene(scene);

        initializeControls();
    }

    public void initializeControls() {
        initializeOkButton();
    }

    private void initializeOkButton() {
        boardName.textProperty().addListener((obj, oldText, newText) -> validate(newText));

        this.validate(boardName.getText());
    }

    public void cancel(ActionEvent event) {
        stage.close();
    }

    public void ok(ActionEvent event) throws IOException {
        Optional<Parent> mainWindow = Window
                .getWindows()
                .stream()
                .map((s) -> s.getScene().getRoot())
                .filter((w) -> w instanceof MainWindow)
                .findFirst();

        if (mainWindow.isPresent()) {
            ((MainWindow) mainWindow.get()).showBoard(boardName.getText());
            stage.close();
        }
    }

    private void validate(String boardNameText) {
        okButton.setDisable(boardNameText.isEmpty());
    }

    public void showAndWait() {
        stage.showAndWait();
    }
}
