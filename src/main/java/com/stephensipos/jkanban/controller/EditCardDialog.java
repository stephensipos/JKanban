package com.stephensipos.jkanban.controller;

import com.stephensipos.jkanban.model.dao.CardDao;
import com.stephensipos.jkanban.model.entities.Card;
import com.stephensipos.jkanban.utils.Categories;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Arrays;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class EditCardDialog extends BorderPane {

    protected Stage stage;
    protected Scene scene;
    protected Card card;

    @FXML protected TextField cardTitle;
    @FXML protected TextArea description;
    @FXML protected Button okButton;
    @FXML protected ComboBox category;

    public EditCardDialog(Card card) throws IOException {
        super();
        this.card = card;

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        scene = new Scene(this);

        initializeFromFxml(this);

        stage.setScene(scene);

        initializeControls();
        cardTitle.requestFocus();
    }

    public void initializeControls() {
        initializeOkButton();
        initializeCategories();

        cardTitle.setText(card.getTitle());
        description.setText(card.getDescription());
        category.setValue(card.getCategory());
    }

    private void initializeCategories() {
        category.getItems().setAll(Categories.getSupportedCategories());
    }

    private void initializeOkButton() {
        cardTitle.textProperty().addListener((obj, oldText, newText) -> validate(newText));
        this.validate(cardTitle.getText());
    }

    public void cancel(ActionEvent event) {
        stage.close();
    }

    public void ok(ActionEvent event) throws IOException {
        card.setTitle(cardTitle.getText());
        card.setDescription(description.getText());
        card.setCategory(category.getValue().toString());
        CardDao.update(card);
        stage.close();
    }

    private void validate(String cardTitleText) {
        okButton.setDisable(cardTitleText.isEmpty());
    }

    public void showAndWait() {
        stage.showAndWait();
    }
}
