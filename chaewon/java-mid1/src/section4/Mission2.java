package section4;

public class Mission2 {
    public static void main(String[] args) {
        String str = "babad";

        for (int i = 0; i < str.length(); i++) {
            for (int j = str.length(); j >= i ; j--) {
                if (isOalindrome(str, i, j - 1)) {
                    System.out.println(str.substring(i, j));

                    return;
                }
            }
        }
    }

    private static boolean isOalindrome(String str, int startIndex, int endIndex) {
        while (startIndex <= endIndex) {
            if (str.charAt(startIndex++) != str.charAt(endIndex--)) {
                return false;
            }
        }

        return true;
    }
}
