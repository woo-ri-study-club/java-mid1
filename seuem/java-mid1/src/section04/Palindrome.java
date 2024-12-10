package section04;

public class Palindrome {
    public static void main(String[] args) {
        String input = "aba";
        //bab 나 aba
        String answer = getLongestPalindrome(input);
        System.out.println("answer = " + answer);
    }

    private static String getLongestPalindrome(String input) {
        if (input.isEmpty() || input==null||input.length() == 1) {
            return input;
        }
        String longestPalindrome = "";
        for (int i = 0; i < input.length(); i++) {
            String oddPalindrome = expendFromCenter(input, i, i+1);
            String evenPalindrome= expendFromCenter(input, i, i);
            if (oddPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = oddPalindrome;
            }
            if (evenPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = evenPalindrome;
            }
        }
        return longestPalindrome;

    }

    private static String expendFromCenter(String input, int left, int right) {
        while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
            left--;
            right++;
        }
        return input.substring(left+1, right);
    }
}
