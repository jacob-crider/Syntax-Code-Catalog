package com.techelevator.model;

import java.util.Objects;

public class Example {

    private int exampleID;
    private String title;
    private String snippet;

    public int getExampleID() {
        return exampleID;
    }

    public void setExampleID(int exampleID) {
        this.exampleID = exampleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Example example = (Example) o;
        return exampleID == example.exampleID && Objects.equals(title, example.title) && Objects.equals(snippet, example.snippet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exampleID, title, snippet);
    }
}
