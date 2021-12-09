package com.techelevator.model;

import java.util.Objects;

public class Language {

    private Integer id;
    private String type;
    private boolean isDeleted;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return isDeleted == language.isDeleted && Objects.equals(id, language.id) && Objects.equals(type, language.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, isDeleted);
    }
}
