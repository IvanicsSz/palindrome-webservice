package io.falcon.assignment.service;

/***
 * PalindromeService is to calculate longest palindrome in string.
 *
 *
 */
public interface PalindromeService {

    /***
     * Calculate the longest substring in the input string. Only for alphabetic characters.
     * @param content
     * @return substring's length if no alphabetic character will return 0
     */
    int getLongestPalindrome(String content);
}
