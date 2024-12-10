package section4;

import java.util.HashMap;
import java.util.Map;

public class Mission1 {
    public static void main(String[] args) {
        String str1 = "Lis  ten";
        String str2 = "Si  lent";

        System.out.println("애너그램 여부: " + isAnagram(str1, str2));

        String str3 = "안녕하세요!!";
        String str4 = "하안녕!!세요";

        System.out.println("애너그램 여부: " + isAnagram(str3, str4));
    }

    private static boolean isAnagram(String str1, String str2) {
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        if (str1.length() != str2.length()) {
            return false;
        }

        Map<Character, Integer> countMap = new HashMap<>();

        for (char oneChar : str1.toCharArray()) {
            countMap.put(oneChar, countMap.getOrDefault(oneChar, 0) + 1);
        }

        for (char oneChar : str2.toCharArray()) {
            if (!countMap.containsKey(oneChar)) {
                return false;
            }

            countMap.put(oneChar, countMap.get(oneChar) - 1);

            if (countMap.get(oneChar) == 0) {
                countMap.remove(oneChar);
            }
        }

        return countMap.isEmpty();
    }
}
