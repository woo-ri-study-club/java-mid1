package section4;

public class Solution2 {

    public static void main(String[] args) {

        String input = "babad";
        System.out.println(findLongestPalindrome(input));
    }



    private static String findLongestPalindrome(String input) {
        if(input.isEmpty() || input.length() == 1){
            return input;
        }

        int len = input.length();
        int maxStart = 0;
        int maxLen = 0;

        for(int center = 0; center<len; center++){
            int left = center;
            int right = center;
            int[] result = findPalindrome(input, left, right);
            if(maxLen < result[1]){
                maxLen = result[1];
                maxStart = result[0];
            }

            left = center;
            right = center + 1;
            result = findPalindrome(input, left, right);
            if(maxLen < result[1]){
                maxLen = result[1];
                maxStart = result[0];
            }

        }

        return input.substring(maxStart, maxStart + maxLen);
    }

    private static int[] findPalindrome(String input, int left, int right) {
        while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
            left--;
            right++;
        }

        left++;
        right--;
        return new int[]{left, right - left + 1};
    }
}
