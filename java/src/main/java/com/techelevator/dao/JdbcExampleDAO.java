package com.techelevator.dao;

import com.techelevator.model.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcExampleDAO implements ExampleDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcExampleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Example> getAllExamples() {
        List<Example> examples = new ArrayList<>();

        String sql = "SELECT example_id, title, snippet FROM examples";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Example example = mapRowToExample(results);
            examples.add(example);
        }
        return examples;
    }

    @Override
    public Example addExample(Example example) {

        String sql = "INSERT INTO examples (example_id, title, snippet) VALUES (DEFAULT, ?, ?) RETURNING example_id";

        Integer exampleId = jdbcTemplate.queryForObject(sql, Integer.class, example.getTitle(), example.getSnippet());

        example.setExampleID(exampleId);

        return example;
    }


    private Example mapRowToExample(SqlRowSet row) {
        Example example = new Example();
        example.setExampleID(row.getInt("example_id"));
        example.setTitle(row.getString("title"));
        example.setSnippet(row.getString("snippet"));

        return example;
    }
}
