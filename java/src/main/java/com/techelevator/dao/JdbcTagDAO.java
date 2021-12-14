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
        List<Tag> allTags = getAllTags();
        String sql = "INSERT INTO example_tag (example_id, tag_id) VALUES (?, (SELECT id FROM tags WHERE name = ?))";

        if (example.getTagList().size() != 0) {

            for (Tag userCurrentTag: example.getTagList()) {
                boolean isMatch = false;

                for (Tag tagInAllTagsList : allTags) {

                    if (userCurrentTag.getName().equals(tagInAllTagsList.getName())) {
                        isMatch = true;
                        jdbcTemplate.update(sql, example.getExampleID(), userCurrentTag.getName());
                    }
                }
                if (!isMatch) {
                    Tag newTag = insertTag(userCurrentTag.getName());
                    jdbcTemplate.update(sql, example.getExampleID(), newTag.getName());
                }
            }
        }
    }

    @Override
    public void removeTags(Example example) {
        String sql = "DELETE FROM example_tag WHERE example_id = ?";

        jdbcTemplate.update(sql, example.getExampleID());
    }

    @Override
    public void replaceTags(Example example) {
        removeTags(example);
        insertTagsForExample(example);
    }

    private List<Tag> getAllTags() {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT id, name FROM tags";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            Tag tag = new Tag();
            tag.setId(results.getInt("id"));
            tag.setName(results.getString("name"));

            tags.add(tag);
        }
        return tags;
    }

    private Tag insertTag(String tagName) {
        Tag tag = new Tag();
        String sql = "INSERT INTO tags (id, name) VALUES (DEFAULT, ?) RETURNING id";

        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, tagName);
        tag.setName(tagName);
        tag.setId(id);

        return tag;
    }
}
