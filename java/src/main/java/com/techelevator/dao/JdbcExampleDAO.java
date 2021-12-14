package com.techelevator.dao;

import com.techelevator.model.Comment;
import com.techelevator.model.Example;
import com.techelevator.model.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcExampleDAO implements ExampleDAO {

    private JdbcTemplate jdbcTemplate;
    private TagDAO tagDAO;
    private CommentDAO commentDAO;

    public JdbcExampleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.tagDAO = new JdbcTagDAO(jdbcTemplate);
        this.commentDAO = new JdbcCommentDAO(jdbcTemplate);
    }

    @Override
    public List<Example> getAllExamples() {
        List<Example> examples = new ArrayList<>();

        String sql = "SELECT example_id, title, snippet, languages.type, languages.id, description, is_public, username, image_url FROM examples " +
                "JOIN languages ON languages.id = examples.language_id;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Example example = mapRowToExample(results);
            List<Tag> tags = tagDAO.getTagsByExampleId(example);
            example.setTagList(tags);
            example.setComments(commentDAO.getCommentsByExampleId(example.getExampleID()));
            examples.add(example);
        }
        return examples;
    }

    @Override
    public Example addExample(Example example, String username) {

        String sql = "INSERT INTO examples (example_id, title, snippet, language_id, description, username, image_url) VALUES (DEFAULT, ?, ?, (SELECT id FROM languages WHERE type = ?), ?, ?, ?) RETURNING example_id";

        Integer exampleId = jdbcTemplate.queryForObject(sql, Integer.class, example.getTitle(), example.getSnippet(), example.getLanguageType(), example.getDescription(), username, example.getImageUrl());

        example.setExampleID(exampleId);
        tagDAO.insertTagsForExample(example);

        return example;
    }

    @Override
    public boolean updateExample(Example example) {
        String sql = "UPDATE examples SET title = ?, snippet = ?, language_id = ?, description = ?, is_public = ?, image_url = ? WHERE example_id = ?";

        int rowCount = jdbcTemplate.update(sql, example.getTitle(), example.getSnippet(), example.getLanguageId(), example.getDescription(), example.isPublic(), example.getImageUrl(), example.getExampleID());
        tagDAO.replaceTags(example);

        return rowCount > 0;
    }

    private Example mapRowToExample(SqlRowSet row) {
        Example example = new Example();
        example.setExampleID(row.getInt("example_id"));
        example.setTitle(row.getString("title"));
        example.setSnippet(row.getString("snippet"));
        example.setLanguageType(row.getString("type"));
        example.setLanguageId(row.getInt("id"));
        example.setDescription(row.getString("description"));
        example.setPublic(row.getBoolean("is_public"));
        example.setUsername(row.getString("username"));
        example.setImageUrl(row.getString("image_url"));

        return example;
    }
}
