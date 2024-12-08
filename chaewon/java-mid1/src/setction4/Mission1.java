package setction4;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Mission1 {
    public static void main(String[] args) {
        String str1 = "Listen";
        String str2 = "Silent";

        System.out.println("애너그램 여부: " + isAnagram(str1, str2));
    }

    static boolean isAnagram(String str1, String str2) {
        char[] str1Array = str1.strip().toLowerCase().toCharArray();
        char[] str2Array = str2.strip().toLowerCase().toCharArray();

        Arrays.sort(str1Array);
        Arrays.sort(str2Array);

        return Arrays.equals(str1Array, str2Array);
    }
}
