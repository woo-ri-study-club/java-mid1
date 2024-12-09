package section4;

import java.util.HashMap;

public class Solution1 {

    public static void main(String[] args) {

        String s1 = "Listen";
        String s2 = "Silent";
        System.out.println(isAnagram(s1, s2));


    }

    public static boolean isAnagram(String str1, String str2) {
        str1 = str1.toLowerCase().replace(" ", "");
        str2 = str2.toLowerCase().replace(" ", "");

        if(str1.length() != str2.length()){
            return false;
        }

        HashMap<Character, Integer> charCount = new HashMap<>();

        for (char ch : str1.toCharArray()) {
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }

        for (char ch : str2.toCharArray()) {
            if (!charCount.containsKey(ch) || charCount.get(ch) == 0) {
                return false;
            }
            charCount.put(ch, charCount.get(ch) - 1);
        }

        for (int count : charCount.values()) {
            if(count != 0){
                return false;
            }
        }

        return true;
    }
}
