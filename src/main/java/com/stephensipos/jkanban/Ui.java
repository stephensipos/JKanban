package com.stephensipos.jkanban;

import com.stephensipos.jkanban.controller.MainWindow;
import com.stephensipos.jkanban.model.dao.GenericDao;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Ui extends Application {
    Scene scene;
    @Override public void start(Stage stage) throws IOException {
        var mainWindow = new MainWindow();

        stage.setScene(mainWindow.getScene());
        stage.setOnCloseRequest( event -> {
            GenericDao.emf.close();
            Platform.exit();
            System.exit(0);
        });

        stage.show();
    }
}
