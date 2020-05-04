package com.stephensipos.jkanban.model.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="card")
public class Card implements Serializable{
    @Id
    @GeneratedValue
    private int id;

    @OneToOne(mappedBy = "card",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private CardBoardRelation relation;

    @Column
    private String title;

    @Column
    private String description;

    private String category;

    public Card() {
        this("", "", "");
    }

    public Card(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
