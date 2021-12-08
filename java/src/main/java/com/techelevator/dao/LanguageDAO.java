package com.techelevator.dao;

import com.techelevator.model.Language;

import java.util.List;

public interface LanguageDAO {

    List<Language> getAllLanguages();
    Language addLanguage(String language);
    Language updateLanguage();
    void deleteLanguage();
}
