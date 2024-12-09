package src.section4.problem1;

public class AnagramMain {
    public static void main(String[] args) {
        String str1 = "Listen";
        String str2 = "Silent";

        boolean anagramIgnoreCase = Anagram.isAnagramIgnoreCase(str1, str2);
        System.out.println(anagramIgnoreCase);
    }
}
