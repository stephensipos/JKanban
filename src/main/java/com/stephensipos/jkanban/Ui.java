package com.stephensipos.jkanban;

import com.stephensipos.jkanban.controller.PrimaryStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Ui extends Application {
    @Override public void start(Stage stage) throws IOException {
        var primaryStage = new PrimaryStage();
        primaryStage.show();
    }
}
