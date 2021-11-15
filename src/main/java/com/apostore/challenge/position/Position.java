package com.apostore.challenge.position;

import com.apostore.challenge.item.Item;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class Position {
    @OneToOne
    private Item item;
    private Integer amount;

    public Position() {
    }

    @JsonCreator
    public Position(Item item, Integer amount) {
        this.item = item;
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Position{" +
                "item=" + item +
                ", amount=" + amount +
                '}';
    }
}
