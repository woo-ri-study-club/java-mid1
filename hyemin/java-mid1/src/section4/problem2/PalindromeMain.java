package src.section4.problem2;

public class PalindromeMain {
    public static void main(String[] args) {
        String input = "babad";
        String longestPalindrome = Palindrome.longestPalindrome(input);
        System.out.println(longestPalindrome); // bab
    }
}
