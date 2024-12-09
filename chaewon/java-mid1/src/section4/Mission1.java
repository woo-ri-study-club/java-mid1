package section4;

import java.util.Arrays;

public class Mission1 {
    public static void main(String[] args) {
        String str1 = "Lis  ten";
        String str2 = "Silent";

        System.out.println("애너그램 여부: " + isAnagram(str1, str2));
    }

    private static boolean isAnagram(String str1, String str2) {
        char[] str1Array = str1.replaceAll("\\s", "").toLowerCase().toCharArray();
        char[] str2Array = str2.replaceAll("\\s", "").toLowerCase().toCharArray();

        Arrays.sort(str1Array);
        Arrays.sort(str2Array);

        return Arrays.equals(str1Array, str2Array);
    }
}
