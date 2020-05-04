package com.stephensipos.jkanban.controller;

import com.stephensipos.jkanban.model.dao.BoardDao;
import com.stephensipos.jkanban.model.entities.Board;
import com.stephensipos.jkanban.utils.Columns;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class BoardPane extends VBox {
    private Board board;
    private Scene scene;

    @FXML private Label boardName;
    @FXML private HBox columns;
    @FXML private Menu boardsMenu;

    public Board getBoard() {
        return board;
    }

    public BoardPane(Scene scene, Board board) throws IOException {
        super();
        this.board = board;
        this.scene = scene;

        initializeFromFxml(this, scene);

        this.boardName.setText(board.getTitle());

        Arrays.stream(Columns.getSupportedColumns()).forEach(column -> {
            try {
                columns.getChildren().add(new ColumnPane(scene, board, column));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        initializeBoardsMenu();
    }

    private void initializeBoardsMenu() {
        BoardDao.findAll().forEach(board -> {
            MenuItem menuItem = new MenuItem(board.getTitle());
            menuItem.setUserData(board);
            menuItem.setOnAction(event -> {
                try {
                    openBoard(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            boardsMenu.getItems().add(menuItem);
        });
    }

    public void close(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void about(ActionEvent actionEvent) {
    }

    public void create(ActionEvent actionEvent) throws IOException {
        var createBoard = new CreateBoardDialog();
        createBoard.showAndWait();
    }

    public void delete(ActionEvent actionEvent) throws IOException {
        BoardDao.delete(board);
        MainWindow mainWindow = (MainWindow) scene.getRoot();
        mainWindow.initializeContent();
    }

    public void openBoard(ActionEvent actionEvent) throws IOException {
        MenuItem source = (MenuItem) actionEvent.getSource();
        Board board = (Board) source.getUserData();
        MainWindow mainWindow = (MainWindow) scene.getRoot();
        mainWindow.showBoard(board);
    }
}
