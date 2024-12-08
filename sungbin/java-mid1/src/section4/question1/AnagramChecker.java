package section4.question1;

import java.util.HashMap;
import java.util.Map;

public class AnagramChecker {

    public static boolean areAnagrams(String str1, String str2) {
        str1 = str1.toLowerCase().replaceAll("\\s+", "");
        str2 = str2.toLowerCase().replaceAll("\\s+", "");

        if (str1.length() != str2.length()) {
            return false;
        }

        Map<Character, Integer> charCountMap = new HashMap<>();

        for (char c : str1.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        for (char c : str2.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) - 1);
        }

        for (int count : charCountMap.values()) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}
