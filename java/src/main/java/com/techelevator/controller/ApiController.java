package com.techelevator.controller;
import com.techelevator.dao.ExampleDAO;
import com.techelevator.model.Example;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class ApiController {

    private ExampleDAO exampleDAO;

    public ApiController(ExampleDAO exampleDAO) {
        this.exampleDAO = exampleDAO;
    }

    @RequestMapping(path="/examples", method=RequestMethod.GET)
    public List<Example> getExamples() {
        return exampleDAO.getAllExamples();
    }
}
