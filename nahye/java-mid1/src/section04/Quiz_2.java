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

        String longest = input.substring(0, 1);

        for (int i = 0; i < input.length(); i++) {

            // 단어의 길이가 홀수일 때
            String oddStr = extractPalindrome(input, i, i);
            if (oddStr.length() > longest.length()) {
                longest = oddStr;
            }

            // 단어의 길이가 짝수일 때
            String evenStr = extractPalindrome(input, i, i + 1);
            if (evenStr.length() > longest.length()) {
                longest = evenStr;
            }
        }

        return longest;
    }

    public static String extractPalindrome(String input, int left, int right) {
        while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
            left--;
            right++;
        }
        return input.substring(left + 1, right);
    }
}
