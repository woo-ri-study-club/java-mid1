package section4;

public class Mission2 {
    public static void main(String[] args) {
        String str = "abcbaabcb";

        System.out.println(longestPalindrome(str));
    }

    private static String longestPalindrome(String str) {
        int length = str.length();
        boolean[][] checkPalindrome = new boolean[length][length];

        int startIndex = 0;
        int maxLength = 0;

        for (int sublength = 1; sublength <= length; sublength++) {
            for (int i = 0; i <= length - sublength; i++) {
                int j = i + sublength - 1;

                switch (sublength) {
                    case 1 -> checkPalindrome[i][j] = true;
                    case 2 -> checkPalindrome[i][j] = (str.charAt(i) == str.charAt(j));
                    default -> checkPalindrome[i][j] = (str.charAt(i) == str.charAt(j)) && checkPalindrome[i + 1][j - 1];
                }

                if (checkPalindrome[i][j] && (sublength > maxLength)) {
                    startIndex = i;
                    maxLength = sublength;
                }
            }
        }

        return str.substring(startIndex, maxLength - startIndex);
    }

}
