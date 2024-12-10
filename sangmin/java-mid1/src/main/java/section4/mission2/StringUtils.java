package section4.mission2;

public class StringUtils {
    private StringUtils() {
    }

    public static String getLongestPalindrome(final String str) {
        if (str.isEmpty()) {
            return "";
        }

        int maxStart = 0;
        int maxEnd = 0;

        for (int i = 0; i < str.length(); i++) {
            int len1 = expandAroundCenter(str, i, i);
            int len2 = expandAroundCenter(str, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > maxEnd - maxStart) {
                maxStart = i - (len - 1) / 2;
                maxEnd = i + len / 2;
            }
        }

        return str.substring(maxStart, maxEnd + 1);
    }

    private static int expandAroundCenter(String str, int left,  int right) {

        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
