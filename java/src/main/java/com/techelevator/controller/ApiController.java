package com.techelevator.controller;
import com.techelevator.dao.ExampleDAO;
import com.techelevator.exception.InvalidAddException;
import com.techelevator.model.Example;
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

    public ApiController(ExampleDAO exampleDAO) {
        this.exampleDAO = exampleDAO;
    }

    @RequestMapping(path="/examples", method=RequestMethod.GET)
    public List<Example> getExamples() {
        return exampleDAO.getAllExamples();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/examples", method=RequestMethod.POST)
    public void addExample(@RequestBody Example example) throws InvalidAddException {
         try {
             exampleDAO.addExample(example);
         } catch (DataAccessException ex) {
             throw new InvalidAddException();
        }
    }
}
