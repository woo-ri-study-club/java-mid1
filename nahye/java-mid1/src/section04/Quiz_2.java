package section04;

public class Quiz_2 {
    public static void main(String[] args) {
        String input = "babad";
        System.out.println(findLongestPalindrome(input));

    }
    public static String findLongestPalindrome(String input) {
        if (input == null || input.length() < 2) {
            return input;
        }

        String longest = "";

       for(int i = 0; i < input.length(); i++) {
           for (int j = i + 1; j < input.length(); j++) {
               String result = extractPalindrome(input, i, j);
               if (result.length() > longest.length()) {
                   longest = result;
               }
           }
       }
        return longest;
    }

    public static String extractPalindrome(String input, int start, int end) {
        int left = start;
        int right = end;

        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return "";
            }
            left++;
            right--;
        }

        return input.substring(start, end + 1);
    }
}
