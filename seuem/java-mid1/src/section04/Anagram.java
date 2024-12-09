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

        StringBuilder sb1 = new StringBuilder(str1);
        StringBuilder sb2 = new StringBuilder(str2);

        for (int i = 0; i < sb2.length(); i++) {
            String string2 = String.valueOf(sb2.charAt(i));
            if (string2.equals(" ")) {
                sb2.deleteCharAt(i);
                i--;
                continue;
            }
            int an = sb1.indexOf(string2);
            if (an == -1) {
                return false;
            }
            sb1.deleteCharAt(an);
        }

        String trim = String.valueOf(sb1);
        String answer = trim.trim();
        return answer.isEmpty();
    }

}
