package com.example.texteditorapi.editor.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Id
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private int cursor;
    private int anchor;
    private int preferredColumn;

    protected DocumentEntity() {
    }

    public DocumentEntity(UUID id, String text, int cursor, int anchor, int preferredColumn) {
        this.id = id;
        this.text = text;
        this.cursor = cursor;
        this.anchor = anchor;
        this.preferredColumn = preferredColumn;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getCursor() {
        return cursor;
    }

    public int getAnchor() {
        return anchor;
    }

    public int getPreferredColumn() {
        return preferredColumn;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public void setAnchor(int anchor) {
        this.anchor = anchor;
    }

    public void setPreferredColumn(int preferredColumn) {
        this.preferredColumn = preferredColumn;
    }
}
