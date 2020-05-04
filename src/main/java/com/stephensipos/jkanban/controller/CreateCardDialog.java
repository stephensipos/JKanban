package com.stephensipos.jkanban.controller;

import com.stephensipos.jkanban.model.dao.CardDao;
import com.stephensipos.jkanban.model.entities.Card;
import com.stephensipos.jkanban.utils.Categories;
import javafx.event.ActionEvent;

import java.io.IOException;

public class CreateCardDialog extends EditCardDialog {
    public CreateCardDialog() throws IOException {
        super(new Card("", "", Categories.getDefaultCategory()));
    }

    public Card getCard() {
        return card;
    }

    @Override
    public void ok(ActionEvent event) throws IOException {
        card.setTitle(cardTitle.getText());
        card.setDescription(description.getText());
        card.setCategory(category.getValue().toString());
        CardDao.save(card);
        stage.close();
    }

}
