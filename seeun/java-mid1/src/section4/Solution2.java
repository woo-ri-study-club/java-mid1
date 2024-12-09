package section4;

public class Solution2 {

    public static void main(String[] args) {

        String input = "babad";
        System.out.println(findLongestPelindrome(input));
    }

    static int maxStart = 0;
    static int maxLen = 0;

    private static String findLongestPelindrome(String input) {
        if(input.isEmpty() || input.length() == 1){
            return input;
        }

        int len = input.length();

        for(int center = 0; center<len; center++){
            int left = center;
            int right = center;
            findPelindrome(len, input, left, right);

            left = center;
            right = center + 1;
            findPelindrome(len, input, left, right);

        }

        return input.substring(maxStart, maxStart + maxLen);
    }

    private static void findPelindrome(int len, String input, int left, int right) {
        while (left >= 0 && right < len && input.charAt(left) == input.charAt(right)) {
            int currLen = right - left + 1;
            if (currLen > maxLen) {
                maxStart = left;
                maxLen = currLen;
            }
            left--;
            right++;
        }
    }
}
