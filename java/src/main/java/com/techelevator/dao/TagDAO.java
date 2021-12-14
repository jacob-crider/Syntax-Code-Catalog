package com.techelevator.dao;

import com.techelevator.model.Example;
import com.techelevator.model.Tag;

import java.util.List;

public interface TagDAO {

    List<Tag> getTagsByExampleId(Example example);

    void insertTagsForExample(Example example);

    void removeTags(Example example);

    void replaceTags(Example example);
}
