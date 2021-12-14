package com.techelevator.model;

import java.util.Objects;

public class Comment {

    private int id;
    private String description;
    private int exampleId;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getExampleId() {
        return exampleId;
    }

    public void setExampleId(int exampleId) {
        this.exampleId = exampleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && exampleId == comment.exampleId && Objects.equals(description, comment.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, exampleId);
    }
}
