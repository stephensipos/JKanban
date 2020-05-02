package com.stephensipos.jkanban.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class BoardPane extends VBox {
    @FXML private Label boardName;
    @FXML private HBox categories;

    public BoardPane(Scene scene, String name) throws IOException {
        super();
        initializeFromFxml(this, scene);
        this.boardName.setText(name);

        var todo = new ColumnPane(scene, "To-do");

        todo.addCard(new CardPane(scene, "Shopping", "Lorem ipsum dolor sit amet,\n\nconsectetur adipiscing elit.\nInteger nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. Vestibulum lacinia arcu eget nulla. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur sodales ligula in libero. Sed dignissim lacinia nunc. Curabitur tortor. Pellentesque nibh. Aenean quam. In scelerisque sem at dolor."));

        categories.getChildren().addAll(
                todo,
                new ColumnPane(scene, "Do today"),
                new ColumnPane(scene, "In progress"),
                new ColumnPane(scene, "Done")
        );

        categories.getChildren().stream().forEach(node -> {
            ((Region) node).prefHeightProperty().bind(categories.heightProperty());
        });
    }

    public void close(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void about(ActionEvent actionEvent) {
    }

    public void createBoard(ActionEvent actionEvent) throws IOException {
        var createBoard = new CreateBoardDialog();
        createBoard.showAndWait();
    }

    public void openBoard(ActionEvent actionEvent) throws IOException {
        ((MainWindow) this.getScene().getRoot()).showBoard(((MenuItem)actionEvent.getSource()).getText());
    }
}
