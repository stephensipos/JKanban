package com.stephensipos.jkanban.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.io.IOException;

public class javafx {
    public static void initializeFromFxml(Node node) throws IOException {
        initializeFromFxml(node, node.getScene());
    }

    public static void initializeFromFxml(Node node, Scene scene) throws IOException {
        loadCss(node, scene);
        loadFxml(node);
    }

    private static String getFxmlName(Node node) {
        return "/fxml/" + node.getClass().getSimpleName() + ".fxml";
    }

    private static String getCssName(Node node) {
        return "/css/" + node.getClass().getSimpleName() + ".css";
    }

    private static void loadCss(Node node, Scene scene) {
        if (!scene.getStylesheets().contains(getCssName(node)) && ResourceChecker.resourceExists(getCssName(node))) {
            scene.getStylesheets().add(getCssName(node));
        }
    }

    private static void loadFxml(Node node) throws IOException {
        FXMLLoader loader = new FXMLLoader(node.getClass().getResource(getFxmlName(node)));
        loader.setRoot(node);
        loader.setController(node);
        loader.load();
    }

}
