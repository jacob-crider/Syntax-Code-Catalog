package com.techelevator.dao;

import com.techelevator.model.Language;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLanguageDAO implements LanguageDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcLanguageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Language> getAllLanguages() {
        List<Language> languages = new ArrayList<>();
        String sql = "SELECT id, type FROM languages";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Language language = mapRowToLanguage(results);
            languages.add(language);
        }
        return languages;
    }

    @Override
    public Language addLanguage(String language) {
        Language newLanguage = new Language();
        String sql = "INSERT INTO languages (id, type) VALUES (DEFAULT, ?) RETURNING id";

        Integer languageId = jdbcTemplate.queryForObject(sql, Integer.class, language);

        newLanguage.setId(languageId);
        newLanguage.setType(language);

        return newLanguage;
    }

    @Override
    public boolean updateLanguage(Language language) {
        String sql = "UPDATE languages SET type = ? WHERE id = ?";

        int rowCount = jdbcTemplate.update(sql, language.getType(), language.getId());

        return rowCount > 0;
    }

    @Override
    public boolean deleteLanguage(Language language) {
        String sql = "DELETE FROM languages WHERE id = ?";

        int rowCount = jdbcTemplate.update(sql, language.getId());

        return rowCount > 0;
    }

    private Language mapRowToLanguage(SqlRowSet row) {
        Language language = new Language();
        language.setId(row.getInt("id"));
        language.setType(row.getString("type"));

        return language;
    }
}
