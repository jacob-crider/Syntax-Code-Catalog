package com.techelevator.controller;

import com.techelevator.dao.ExampleDAO;
import com.techelevator.dao.LanguageDAO;
import com.techelevator.exception.BadRequestException;
import com.techelevator.model.Example;
import com.techelevator.model.Language;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class ApiController {

    private ExampleDAO exampleDAO;
    private LanguageDAO languageDAO;

    public ApiController(ExampleDAO exampleDAO, LanguageDAO languageDAO) {
        this.exampleDAO = exampleDAO;
        this.languageDAO = languageDAO;
    }

    @RequestMapping(path = "/examples", method = RequestMethod.GET)
    public List<Example> getExamples() {
        return exampleDAO.getAllExamples();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/examples", method = RequestMethod.POST)
    public void addExample(@RequestBody Example example) throws BadRequestException {
        try {
            exampleDAO.addExample(example);
        } catch (DataAccessException ex) {
//            throw new BadRequestException();
            ex.printStackTrace();
        }
    }

    @RequestMapping(path = "/languages", method = RequestMethod.GET)
    public List<Language> getAllLanguages() {
        return languageDAO.getAllLanguages();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/languages", method = RequestMethod.POST)
    public void addLanguage(@RequestBody Language language) throws BadRequestException {
        try {
            languageDAO.addLanguage(language.getType());
        } catch (DataAccessException e) {
            throw new BadRequestException();
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/languages", method = RequestMethod.PUT)
    public void updateLanguage(@RequestBody Language language) throws BadRequestException {
        try {
            boolean updated = languageDAO.updateLanguage(language);
            if (!updated) {
                throw new BadRequestException();
            }
        } catch (DataAccessException e) {
            throw new BadRequestException();
        }
    }

}
