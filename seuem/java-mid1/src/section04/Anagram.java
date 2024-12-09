package section04;

public class Anagram {
    public static void main(String[] args) {
        String str1 = "  Li  sten   ";
        String str2 = "Sil  ent";

        System.out.println("answer = " + anagram(str1, str2));
    }

    private static boolean anagram(String str1, String str2) {

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        str1 = str1.replace(" ", "");
        str2 = str2.replace(" ", "");

        StringBuilder sb1 = new StringBuilder(str1);

        for (int i = 0; i < str2.length(); i++) {
            int an = sb1.indexOf(str2.substring(i, i + 1));
            if (an == -1) {
                return false;
            }
            sb1.deleteCharAt(an);
        }

        return sb1.isEmpty();
    }

}
