package src.section4.problem2;

public class Palindrome {
    public static String longestPalindrome(String s) {
        if (s == null) {
            throw new IllegalArgumentException("유효하지 않은 문자열입니다.");
        }
        for (int i = s.length(); i > 0; i--) {
            for (int j = 0; j+i <= s.length(); j++) {
                if (isPalindromeInRange(s, j, j + i - 1)) {
                    return s.substring(j, j + i);
                }
            }
        }
        return "";
    }

    public static boolean isPalindromeInRange(String s, int start, int end) {
        if (s == null) {
            throw new IllegalArgumentException("유효하지 않은 문자열입니다.");
        }

        if (start < 0 || end >= s.length() || start > end) {
            throw new IllegalArgumentException("유효하지 않은 범위입니다.");
        }

        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
