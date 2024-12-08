package setction4;

public class Mission1 {
    public static void main(String[] args) {
        String str1 = "Listen";
        String str2 = "Silent";

        System.out.println("애너그램 여부: " + isAnagram(str1, str2));
    }

    static boolean isAnagram(String str1, String str2) {
        str1 = str1.strip().toLowerCase();
        str2 = str2.strip().toLowerCase();

        char[] str1Array = str1.toCharArray();
        for (char str1Char : str1Array) {
            String letter = String.valueOf(str1Char);

            if (str2.contains(letter)) {
                str2 = str2.replaceFirst(letter, "");
            }
        }

        if (str2.isEmpty()) {
            return true;
        }

        return false;
    }
}
