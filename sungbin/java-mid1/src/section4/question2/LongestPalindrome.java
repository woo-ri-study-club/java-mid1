package section4.question2;

public class LongestPalindrome {

    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        int longestStart = 0;
        int longestEnd = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // 홀수길이
            int len2 = expandAroundCenter(s, i, i + 1); // 짝수길이
            int len = Math.max(len1, len2); // 최대 길이

            if (len > (longestEnd - longestStart)) {
                longestStart = i - (len - 1) / 2;
                longestEnd = i + len / 2;
            }
        }

        return s.substring(longestStart, longestEnd + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
