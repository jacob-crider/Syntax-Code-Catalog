package com.techelevator.dao;

import com.techelevator.model.Example;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcExampleDAOTest extends DAOIntegrationTest {

    private ExampleDAO exampleDAO;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        exampleDAO = new JdbcExampleDAO(jdbcTemplate);
    }

    @Test
    public void get_all_examples() {
        //insertLanguage("Test");
        List<Example> expectedList = new ArrayList<>();
        String sql = "TRUNCATE TABLE examples CASCADE";
        jdbcTemplate.update(sql);

        expectedList.add(insertExample("test example one", "test example one"));
        expectedList.add(insertExample("test example two", "test example two"));

        List<Example> testExampleList = exampleDAO.getAllExamples();

        Assert.assertEquals(expectedList, testExampleList);
    }

    @Test
    public void add_example() {
        //Arrange
        Example example = new Example();
        example.setSnippet("Test");
        example.setTitle("Title");
        //Act
        example = exampleDAO.addExample(example);
        Example actualResult = selectExampleById(example.getExampleID());
        //Assert
        Assert.assertEquals(example, actualResult);
    }

    private Integer insertLanguage(String language) {
        String sql = "INSERT INTO languages (id, type) VALUES (DEFAULT, ?) RETURNING id";
        return jdbcTemplate.queryForObject(sql, Integer.class, language);
    }

    private Example insertExample(String title, String snippet) {
        String testLanguage = "Test language";
        Integer languageId = insertLanguage(testLanguage);
        String sql = "INSERT INTO examples (example_id, title, snippet, language_id) VALUES (DEFAULT, ?, ?, ?) RETURNING example_id";
        Integer exampleId = jdbcTemplate.queryForObject(sql, Integer.class, title, snippet, languageId);

        Example example = new Example();
        example.setExampleID(exampleId);
        example.setTitle(title);
        example.setSnippet(snippet);
       // example.setLanguage(testLanguage);

        return example;
    }

    private Example mapRowToExample(SqlRowSet row) {
        Example example = new Example();
        example.setExampleID(row.getInt("example_id"));
        example.setTitle(row.getString("title"));
        example.setSnippet(row.getString("snippet"));
      //  example.setLanguage(row.getString("languages.type"));

        return example;
    }

    private Example selectExampleById(int example_id) {
        Example example = new Example();

        String sql = "SELECT example_id, title, snippet, languages.type FROM examples " +
                                "JOIN languages ON languages.id = examples.language_id WHERE example_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, example_id);

        while (results.next()) {
            example = mapRowToExample(results);
        }
        return example;
    }

}
