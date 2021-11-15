package com.apostore.challenge.order;

import com.apostore.challenge.item.Item;
import com.apostore.challenge.position.Position;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.h2.util.json.JSONItemType;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import java.util.*;

@Entity(name = "Order")
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    private Date created;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated;
    @ElementCollection
    @CollectionTable(name = "positions", joinColumns = @JoinColumn(name = "position"), foreignKey = @ForeignKey(name = "order_positions_fk"))
    private List<Position> positions;

    public Order() {
    }

    public Order(Date created, Date updated, List<Position> positions) {
        this.created = created;
        this.updated = updated;
        this.positions = positions;
    }

    public Order(UUID id, Date created, Date updated, List<Position> positions) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.positions = positions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", positions=" + positions +
                '}';
    }
}