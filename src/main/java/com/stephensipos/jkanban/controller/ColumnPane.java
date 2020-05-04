package com.stephensipos.jkanban.controller;

import com.stephensipos.jkanban.model.dao.CardBoardRelationDao;
import com.stephensipos.jkanban.model.dao.CardDao;
import com.stephensipos.jkanban.model.entities.Board;
import com.stephensipos.jkanban.model.entities.Card;
import com.stephensipos.jkanban.model.entities.CardBoardRelation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class ColumnPane extends Pane {
    private String name;
    private Board board;
    private Scene scene;

    @FXML private Label title;
    @FXML private VBox contents;

    public String getName() {
        return name;
    }

    public ColumnPane(Scene scene, Board board, String name) throws IOException {
        super();
        this.board = board;
        this.name = name;
        this.scene = scene;

        initializeFromFxml(this, scene);
        this.title.setText(name);
        this.setEventHandler(ColumnChangedEvent.COLUMN_CHANGED_EVENT_TYPE, event ->
            Platform.runLater(new Runnable() {
                public void run() {
                    loadCards();
                }
            })
        );

        loadCards();
    }

    private void loadCards() {
        this.contents.getChildren().clear();
        CardBoardRelationDao.findCards(board.getId(), name).forEach(card -> {
            try {
                addCard(new CardPane(scene, card));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void addCard(CardPane card) {
        // ((Region) card).prefWidthProperty().bind(contents.widthProperty());
        contents.getChildren().add(card);
    }

    public void createCard(ActionEvent actionEvent) throws IOException {
        var dialog = new CreateCardDialog();
        dialog.showAndWait();

        Card card = dialog.getCard();

        if (card.getId() != 0) {
            int position = CardBoardRelationDao.getNextPosition(board, getName());
            CardBoardRelation rel = new CardBoardRelation(card, board, getName(), position);
            CardBoardRelationDao.save(rel);
        }

        loadCards();
    }

    public void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasContent(CardPane.dataFormat)) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void handleDragDropped(DragEvent event) {
        int cardId = (int) event.getDragboard().getContent(CardPane.dataFormat);
        Card droppedCard = CardDao.find(cardId);
        int position = CardBoardRelationDao.getNextPosition(board, getName());
        CardBoardRelationDao.update(droppedCard, board, getName(), position);
        this.fireEvent(new ColumnChangedEvent());
    }
}
