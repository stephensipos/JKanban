package com.stephensipos.jkanban.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "board",
            cascade = CascadeType.REMOVE)
    private List<CardBoardRelation> relation = new ArrayList<>();

    @Column
    private String title;

    public Board() {
        this("");
    }

    public Board(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
