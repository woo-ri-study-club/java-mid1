package section04;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
    public static void main(String[] args) {
        String str1 = "  Li  sten   ";
        String str2 = "Sil  ent";

        System.out.println("answer = " + isItAnagram(str1, str2));
    }

    private static boolean isItAnagram(String str1, String str2) {

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        str1 = str1.toLowerCase().replaceAll("\\s+", "");
        str2 = str2.toLowerCase().replaceAll("\\s+", ""); //이게 /t /n이런것도 뺴주나봄!

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            map1.put(str1.charAt(i), map1.getOrDefault(str1.charAt(i), 0) + 1);
            map2.put(str2.charAt(i), map2.getOrDefault(str2.charAt(i), 0) + 1);
        }

        return map1.equals(map2);

    }

}
