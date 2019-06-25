package io.falcon.assignment.controller;


import io.falcon.assignment.model.Palindrome;
import io.falcon.assignment.model.dto.RestResponseDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

public class MessageControllerIntegrationTest {

    private static final String GET_CONTENTS = "http://localhost:8080/api/contents";
    private static final String POST_CONTENT = "http://localhost:8080/api/content/send";

    private RestTemplate restTemplate;
    private Date date;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        date = new Date();
    }

    @Test
    public void contentsIsStatusOK() {
        ResponseEntity<String> response
                = restTemplate.getForEntity(GET_CONTENTS, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void validPostRequest(){
        HttpEntity<Palindrome> request = new HttpEntity<>(new Palindrome("abracadabra", date));
        RestResponseDTO response = restTemplate.postForObject(POST_CONTENT, request, RestResponseDTO.class);
        assertThat(response, notNullValue());
        assertThat(response.getSuccess(), is(true));
    }

    @Test
    public void noContentPostRequest() {
        try {
            HttpEntity<Palindrome> request = new HttpEntity<>(new Palindrome("", date));
            RestResponseDTO response = restTemplate.postForObject(POST_CONTENT, request, RestResponseDTO.class);
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        } catch (Exception e) {
            fail("this isn't the expected exception: " + e.getMessage());
        }
    }

    @Test
    public void noTimeStampPostRequest() {
        try {
            HttpEntity<Palindrome> request = new HttpEntity<>(new Palindrome("abba", null));
            RestResponseDTO response = restTemplate.postForObject(POST_CONTENT, request, RestResponseDTO.class);
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        } catch (Exception e) {
            fail("this isn't the expected exception: " + e.getMessage());
        }
    }
}