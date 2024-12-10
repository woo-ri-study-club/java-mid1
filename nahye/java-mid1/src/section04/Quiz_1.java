package section04;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Quiz_1 {
    public static void main(String[] args) {
        String str1 = "Liste  n";
        String str2 = "Silent";

        boolean result = isAnagram(str1, str2);
        System.out.println(result);
    }

    public static boolean isAnagram(String str1, String str2) {
        int[] alphabet= new int[26];

        // 입력값에 대한 처리
        if(str1==null || str2==null ) {
            return false;
        }
        if (str1.isEmpty() || str2.isEmpty()) {
            return false;
        }
        if (!str1.matches("[a-zA-Z\\s]+") || !str2.matches("[a-zA-Z\\s]+")) {
            return false;
        }

        // 공백제거 및 소문자로 변경
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        if (str1.length() != str2.length()) {
            return false;
        }

        char[] charArray1 = str1.toCharArray();
        for (char c : charArray1) {
            alphabet[c - 'a']++;
        }

        char[] charArray2 = str2.toCharArray();
        for (char c : charArray2) {
            alphabet[c - 'a']--;
        }

        for( int i : alphabet) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }
}