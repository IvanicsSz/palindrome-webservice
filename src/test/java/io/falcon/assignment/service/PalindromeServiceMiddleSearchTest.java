package io.falcon.assignment.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PalindromeServiceMiddleSearchTest {

    @InjectMocks
    private PalindromeServiceMiddleSearch palindromeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void abracadabra() {
        Assert.assertEquals(3, getLongestPalindrome("abracadabra"));
    }

    @Test
    public void onlyNumbers() {
        Assert.assertEquals(0, getLongestPalindrome("123456"));
    }

    @Test
    public void numbersAndChars() {
        Assert.assertEquals(4, getLongestPalindrome("123abba456"));
    }

    @Test
    public void noPalindrome() {
        Assert.assertEquals(1, getLongestPalindrome("result"));
    }

    @Test
    public void words() {
        Assert.assertEquals(8, getLongestPalindrome("I did, did I?"));
    }

    private int getLongestPalindrome(String content){
        return palindromeService.getLongestPalindrome(content);
    }
}