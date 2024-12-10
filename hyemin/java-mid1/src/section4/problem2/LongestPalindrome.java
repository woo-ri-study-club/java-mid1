package src.section4.problem2;

public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // 홀수 길이 팰린드롬
            int len2 = expandAroundCenter(s, i, i + 1); // 짝수 길이 팰린드롬
            int len = Math.max(len1, len2);

            if (len > (end - start)) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // 현재 팰린드롬의 길이
    }
}
