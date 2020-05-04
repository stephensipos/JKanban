package com.stephensipos.jkanban.controller;

import com.stephensipos.jkanban.model.dao.CardBoardRelationDao;
import com.stephensipos.jkanban.model.dao.CardDao;
import com.stephensipos.jkanban.model.entities.Card;
import com.stephensipos.jkanban.model.entities.CardBoardRelation;
import com.stephensipos.jkanban.utils.Categories;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;

import static com.stephensipos.jkanban.utils.javafx.initializeFromFxml;

public class CardPane extends Pane {
    private Card card;
    private Scene scene;

    @FXML private Label title;
    @FXML private Label description;
    @FXML private VBox contents;
    @FXML private Menu changeCategoryMenu;
    @FXML private Separator dragBeforeMark;

    public static DataFormat dataFormat = new DataFormat(CardPane.class.getCanonicalName());

    public CardPane(Scene scene, Card card) throws IOException {
        super();
        this.card = card;
        this.scene = scene;

        initializeFromFxml(this, scene);

        this.title.setText(card.getTitle());
        this.description.setText(card.getDescription());
        setCategoryStyle(card.getCategory());

        initializeChangeCategoryMenu();
    }

    private void initializeChangeCategoryMenu() {
        Arrays.stream(Categories.getSupportedCategories()).forEach(category -> {
            MenuItem menuItem = new MenuItem(category);
            menuItem.setOnAction(event -> changeCategory(event));
            changeCategoryMenu.getItems().add(menuItem);
        });
    }
    private void setCategoryStyle(String category) {
        getStyleClass().removeIf(s -> s.startsWith("category-"));
        getStyleClass().add("category-" + category.toLowerCase());
    }

    public void changeCategory(ActionEvent actionEvent) {
        String category = ((MenuItem)actionEvent.getSource()).getText();
        card.setCategory(category);
        CardDao.update(card);
        setCategoryStyle(category);
    }

    public void edit(ActionEvent actionEvent) throws IOException {
        var dialog = new EditCardDialog(card);
        dialog.showAndWait();
        this.fireEvent(new ColumnChangedEvent());
    }

    public void delete(ActionEvent actionEvent) {
        CardDao.delete(card);
        this.fireEvent(new ColumnChangedEvent());
    }

    public void handleDragDetection(MouseEvent event) {
        this.setScaleX(0.9);
        this.setScaleY(0.9);
        Dragboard db = startDragAndDrop(TransferMode.ANY);
        ClipboardContent clip = new ClipboardContent();
        clip.put(dataFormat, card.getId());
        db.setContent(clip);

        event.consume();
    }

    public void handleDragDone(DragEvent event) {
        this.setScaleX(1.0);
        this.setScaleY(1.0);
        this.fireEvent(new ColumnChangedEvent());
    }

    public void handleDragOver(DragEvent event) {
        int cardId = (int) event.getDragboard().getContent(CardPane.dataFormat);
        if (cardId != card.getId()) {
            if (event.getDragboard().hasContent(CardPane.dataFormat)) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        }
    }

    public void handleDragDropped(DragEvent event) {
        int cardId = (int) event.getDragboard().getContent(CardPane.dataFormat);
        if (cardId != card.getId()) {
            Card droppedCard = CardDao.find(cardId);
            CardBoardRelation relation = CardBoardRelationDao.find(card);
            CardBoardRelationDao.slideDown(relation.getBoard(), relation.getColumnName(), relation.getPosition());
            CardBoardRelationDao.update(droppedCard, relation.getBoard(), relation.getColumnName(), relation.getPosition());
            this.fireEvent(new ColumnChangedEvent());
        }
        event.consume();
    }

    public void handleDragEntered(DragEvent event) {
        setTranslateY(15);
        dragBeforeMark.setVisible(true);
    }

    public void handleDragExited(DragEvent event) {
        dragBeforeMark.setVisible(false);
        setTranslateY(0);
    }
}
