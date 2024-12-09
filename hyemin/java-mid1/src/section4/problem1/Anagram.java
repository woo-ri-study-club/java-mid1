package src.section4.problem1;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
    public static boolean isAnagramIgnoreCase(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("유효하지 않은 문자열입니다.");
        }

        String str1 = preprocessString(s1);
        String str2 = preprocessString(s2);

        if (str1.length() != str2.length()) {
            return false;
        }

        Map<Character, Integer> charCntMap = getCharCountMap(str1);

        return checkAnagram(str2, charCntMap);
    }

    private static boolean checkAnagram(String s, Map<Character, Integer> charCntMap) {
        for (char c : s.toCharArray()) {
            if (!charCntMap.containsKey(c)) {
                return false;
            }
            charCntMap.put(c, charCntMap.get(c) - 1);
            if (charCntMap.get(c) < 0) {
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Integer> getCharCountMap(String str1) {
        Map<Character, Integer> charCountMap = new HashMap<>();

        for (char c : str1.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        return charCountMap;
    }

    private static String preprocessString(String s) {
        return s.replaceAll("\\s", "").toLowerCase(); // 하나 이상의 공백 문자를 제거 + 소문자 변환
    }
}
