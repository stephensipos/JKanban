package com.stephensipos.jkanban.controller;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class MainWindow extends Pane {
    public Scene scene;

    public MainWindow() throws IOException {
        scene = new Scene(this);
        initializeFromFxml(this);
        showWelcome();
    }

    private void setContent(Node content) {
        ((Region) content).prefHeightProperty().bind(this.heightProperty());
        ((Region) content).prefWidthProperty().bind(this.widthProperty());

        this.getChildren().setAll(content);
    }

    public void showWelcome() throws IOException {
        setContent(new Welcome(scene));
    }

    public void showBoard(String name) throws IOException {
        setContent(new Board(name, scene));
    }
}
