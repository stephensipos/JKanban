package com.stephensipos.jkanban.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="card_board_relation")
public class CardBoardRelation implements Serializable {
    @Id
    @OneToOne
    private Card card;

    @ManyToOne
    private Board board;

    @Column(nullable = false)
    private String columnName;

    @Column
    private int position;

    public CardBoardRelation() {
    }

    public CardBoardRelation(Card card, Board board, String columnName, int position) {
        this.card = card;
        this.board = board;
        this.columnName = columnName;
        this.position = position;
    }

    public Card getCard() {
        return card;
    }

    public Board getBoard() {
        return board;
    }

    public String getColumnName() {
        return columnName;
    }

    public int getPosition() {
        return position;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(card.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CardBoardRelation other = (CardBoardRelation) obj;
        return Objects.equals(card, other.getCard());
    }
}
