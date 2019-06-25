package io.falcon.assignment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/***
 * PalindromeService implementation of O(n^2) time and O(1) space method
 * to find the longest palindromic substring. Starts searching in the middle of the string
 */
@Service("palindromeMiddleSearch")
@Slf4j
public class PalindromeServiceMiddleSearch implements PalindromeService {

    @Override
    public int getLongestPalindrome(String content) {
        if (content == null) return 0;
        content = content.replaceAll("[^A-Za-z]", "");
        if (content.isEmpty()) return 0;

        String longest = content.substring(0, 1);
        for (int i = 0; i < content.length() - 1; i++) {
            //odd cases ex:aba
            String palindrome = intermediatePalindrome(content, i, i);
            if (palindrome.length() > longest.length()) {
                longest = palindrome;
            }
            //even cases ex:abba
            palindrome = intermediatePalindrome(content, i, i + 1);
            if (palindrome.length() > longest.length()) {
                longest = palindrome;
            }
        }
        log.trace("Longest palindrome is {} size: {}", longest, longest.length());
        return longest.length();

    }
    //search for substring palindrome
    private String intermediatePalindrome(String s, int left, int right) {
        if (left > right) return null;
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
}

