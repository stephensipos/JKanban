package com.stephensipos.jkanban;

import com.stephensipos.jkanban.controller.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Ui extends Application {
    Scene scene;
    @Override public void start(Stage stage) throws IOException {

        var mainWindow = new MainWindow();

        stage.setScene(mainWindow.getScene());

        stage.show();
    }
}
