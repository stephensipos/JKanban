package com.stephensipos.jkanban.controller;

import com.stephensipos.jkanban.utils.ResourceChecker;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public interface ICustomComponent {
    default String getFxmlName() {
        return "/fxml/" + getClass().getSimpleName() + ".fxml";
    }

    default String getCssName() {
        return "/css/" + getClass().getSimpleName() + ".css";
    }

    default void initialize(Stage stage, Parent parent) throws IOException {
        setupStage(stage, parent);
        loadFxml(parent);
        initializeControls();
    }

    default void setupStage(Stage stage, Parent parent) {
        Scene scene = new Scene(parent);
        if (ResourceChecker.resourceExists(getCssName())) {
            scene.getStylesheets().add(getCssName());
        }
        stage.setScene(scene);
    }

    default void loadFxml(Parent parent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(getFxmlName()));
        loader.setRoot(parent);
        loader.setController(parent);
        loader.load();
    }

    default void initializeControls() {
    }
}
