package com.stephensipos.jkanban.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class CardPane extends Pane {
    @FXML private Label title;
    @FXML private Label description;
    @FXML private VBox contents;

    public CardPane(Scene scene, String name, String description) throws IOException {
        super();
        initializeFromFxml(this, scene);
        this.title.setText(name);
        this.description.setText(description);
    }

    private void setCategoryStyle(String category) {
        this.getStyleClass().removeIf(s -> s.startsWith("category-"));
        this.getStyleClass().add("category-" + category);
    }

    public void setCategory(ActionEvent actionEvent) {
        setCategoryStyle(((MenuItem)actionEvent.getSource()).getText().toLowerCase());
    }
}
