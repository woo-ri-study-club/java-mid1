package section4.mission2;

public class Main {
    public static void main(String[] args) {
        String result1 = StringUtils.getLongestPalindrome("babad");
        String result2 = StringUtils.getLongestPalindrome("abcdcba");

        System.out.println("result1 : " + result1);
        System.out.println("result2 : " + result2);
    }
}
