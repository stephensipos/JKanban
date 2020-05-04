package com.stephensipos.jkanban.controller;

import com.stephensipos.jkanban.model.dao.BoardDao;
import com.stephensipos.jkanban.model.entities.Board;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.List;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class MainWindow extends Pane {
    public Scene scene;

    public MainWindow() throws IOException {
        super();
        scene = new Scene(this);
        initializeFromFxml(this);

        initializeContent();
    }

    public void initializeContent() throws IOException {
        List<Board> boards = BoardDao.findAll();
        if (boards.size() > 0) {
            showBoard(boards.get(0));
        } else {
            showWelcome();
        }
    }

    private void setContent(Node content) {
        ((Region) content).prefHeightProperty().bind(this.heightProperty());
        ((Region) content).prefWidthProperty().bind(this.widthProperty());

        this.getChildren().setAll(content);
    }

    public void showWelcome() throws IOException {
        setContent(new WelcomePane(scene));
    }

    public void showBoard(Board board) throws IOException {
        setContent(new BoardPane(scene, board));
    }
}
