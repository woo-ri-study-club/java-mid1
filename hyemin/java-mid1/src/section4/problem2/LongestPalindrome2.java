package src.section4.problem2;

public class LongestPalindrome2 {
    public static String longestPalindrome(String s) {
        int len = s.length();
        int startIdx = 0, maxLen = 0;

        if (len < 2) return s;

        for (int i = 0; i < len - 1; i++) {
            // 홀수 길이 팰린드롬
            int[] resultOdd = extendPalindrome(s, i, i);
            // 짝수 길이 팰린드롬
            int[] resultEven = extendPalindrome(s, i, i + 1);

            if (resultOdd[1] > maxLen) {
                maxLen = resultOdd[1];
                startIdx = resultOdd[0];
            }
            if (resultEven[1] > maxLen) {
                maxLen = resultEven[1];
                startIdx = resultEven[0];
            }
        }

        return s.substring(startIdx, startIdx + maxLen);
    }

    private static int[] extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // 시작 인덱스와 길이 반환
        return new int[]{left + 1, right - left - 1};
    }
}

