package src.section4.problem2;

public class PalindromeMain {
    public static void main(String[] args) {
        String input = "babad";
        String longestPalindrome = LongestPalindrome.longestPalindrome(input);
        String longestPalindrome2 = LongestPalindrome2.longestPalindrome(input);
        System.out.println(longestPalindrome); // bab
        System.out.println(longestPalindrome2); // bab
    }
}
