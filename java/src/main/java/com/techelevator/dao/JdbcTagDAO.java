package com.techelevator.dao;

import com.techelevator.model.Example;
import com.techelevator.model.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcTagDAO implements TagDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcTagDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tag> getTagsByExampleId(Example example) {
        List<Tag> tags = new ArrayList<>();

        String sql = "SELECT tags.name, tags.id FROM examples " +
                "JOIN example_tag ON examples.example_id = example_tag.example_id " +
                "JOIN tags ON tags.id = example_tag.tag_id WHERE examples.example_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, example.getExampleID());

        while(results.next()) {
            Tag tag = new Tag();
            tag.setId(results.getInt("id"));
            tag.setName(results.getString("name"));

            tags.add(tag);
        }
        return tags;
    }

    @Override
    public void insertTagsForExample(Example example) {
        String sql = "INSERT INTO example_tag (example_id, tag_id) VALUES (?, (SELECT id FROM tags WHERE name = ?))";

        if (example.getTagList().size() != 0) {
            for (Tag currentTag: example.getTagList()) {
                jdbcTemplate.update(sql, example.getExampleID(), currentTag.getName());
            }
        }
    }
}
