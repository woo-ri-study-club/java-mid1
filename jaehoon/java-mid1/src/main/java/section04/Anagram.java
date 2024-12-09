package study.section04;

import java.util.HashMap;
import java.util.Map;

public class Anagram {

  public static void main(String[] args) {
    String str1 = "Listen";
    String str2 = "Silent";

    boolean result = solution(str1, str2);
    System.out.println("result = " + result);

  }

  public static boolean solution(String str1, String str2) {
    Map<Character, Integer> str1Map = new HashMap<>();
    Map<Character, Integer> str2Map = new HashMap<>();

    str1 = str1.toLowerCase();
    str2 = str2.toLowerCase();

    if (str1.length() != str2.length()) {
      return false;
    }

    for (char ch : str1.toCharArray()) {
      str1Map.put(ch, str1Map.getOrDefault(ch, 0) + 1);
    }

    for (char ch : str2.toCharArray()) {
      str2Map.put(ch, str2Map.getOrDefault(ch, 0) + 1);
    }

    return str1Map.equals(str2Map);
  }
}
