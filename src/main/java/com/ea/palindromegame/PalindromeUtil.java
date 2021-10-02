package com.ea.palindromegame;

public class PalindromeUtil {

    /**
     * Removes all non-alphanumeric characters, and transforms the chars to lower case
     * @param word String input of any character set
     * @return lower case alphanumeric string
     */
    private static String cleanInput(String word) {
        return word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    /**
     * Computes a score for a string that is equal to the floor of half the count of the length of the palindrome
     * @param word String input of any character set
     * @return integer score for the given palindrome.
     */
    public static int score(String word) {
        if (word == null) return 0;
        String cleanWord = cleanInput(word);
        if (cleanWord.length() < 2 || word == null) return 0;
        int i = 0, j = cleanWord.length() - 1;
        while (i < j) {
            if (cleanWord.charAt(i) != cleanWord.charAt(j)) {
                // Not a palindrome => no points
                return 0;
            }
            i++;
            j--;
        }
        return cleanWord.length()/2;
    }
}
