package com.techelevator.dao;

import com.techelevator.model.Example;
import com.techelevator.model.Tag;

import java.util.List;

public interface TagDAO {

    List<Tag> getTagsByExampleId(Example example);
}
