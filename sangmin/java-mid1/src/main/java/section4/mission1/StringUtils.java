package section4.mission1;

import java.util.HashMap;

public class StringUtils {
    private StringUtils() {
    }

    public static boolean isAnagram(String str1, String str2) {
        str1 = getIgnoreCaseString(str1);
        str2 = getIgnoreCaseString(str2);

        if (notEqualsStringLength(str1, str2)) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            map.put(str1.charAt(i), map.getOrDefault(str1.charAt(i), 0) + 1);
            map.put(str2.charAt(i), map.getOrDefault(str2.charAt(i), 0) - 1);
        }

        for (int value : map.values()) {
            if (value != 0) {
                return false;
            }
        }

        return true;
    }

    private static String getIgnoreCaseString(String str1) {
        return str1.toLowerCase().replaceAll("\\s", "");
    }

    private static boolean equalsStringLength(final String str1, final String str2) {
        return str1.length() == str2.length();
    }

    private static boolean notEqualsStringLength(final String str1, final String str2) {
        return !equalsStringLength(str1, str2);
    }
}
