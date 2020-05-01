package com.stephensipos.jkanban.controller;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class PrimaryStage extends Pane implements ICustomComponent {
    private Stage stage = new Stage();

    public PrimaryStage(Stage stage) throws IOException {
        initialize(stage, this);
        setView(null);
    }


    public void show() {
        stage.show();
    }

    public void setView(String boardName) throws IOException {

        Node content;
        if (boardName == null) {
            content = new Welcome();
        } else {
            content = new Board(boardName);
        }

        ((Region) content).prefHeightProperty().bind(this.heightProperty());
        ((Region) content).prefWidthProperty().bind(this.widthProperty());

        this.getChildren().setAll(content);
    }


}
