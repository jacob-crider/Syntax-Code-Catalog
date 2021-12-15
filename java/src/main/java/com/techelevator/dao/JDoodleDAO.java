package com.techelevator.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.CompiledResponse;
import com.techelevator.model.Execution;
import org.apache.catalina.connector.Request;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JDoodleDAO {

    public RestTemplate restTemplate = new RestTemplate();

    public CompiledResponse compile(Execution execution) {

        String url = "https://api.jdoodle.com/v1/execute";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        // headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        // build the request
        HttpEntity<Execution> entity = new HttpEntity<>(execution, headers);

        // send POST request
        CompiledResponse compiledResponse = restTemplate.postForObject(url, entity, CompiledResponse.class);

        // check response status code
        return compiledResponse;
    }

}
