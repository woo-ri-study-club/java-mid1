package section4.mission2;

public class StringUtils {
    private StringUtils() {
    }

    public static String getLongestPalindrome(final String str) {
        if (str.isEmpty()) {
            return "";
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < str.length(); i++) {
            int len1 = expandAroundCenter(str, i, i);
            int len2 = expandAroundCenter(str, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return str.substring(start, end + 1);
    }

    private static int expandAroundCenter(String str, int leftIndex,  int rightIndex) {

        int left = leftIndex;
        int right = rightIndex;

        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
