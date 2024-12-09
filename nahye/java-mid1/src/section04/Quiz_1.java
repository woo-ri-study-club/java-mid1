package section04;

import java.util.Arrays;


public class Quiz_1 {
    public static void main(String[] args) {
        String str1 = "Listen";
        String str2 = "Silent";

        boolean result = isAnagram(str1, str2);
        System.out.println(result);
    }

    public static boolean isAnagram(String str1, String str2) {

        if (str1.length() != str2.length()) {
            return false;
        }
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        return Arrays.equals(charArray1, charArray2);
    }
}