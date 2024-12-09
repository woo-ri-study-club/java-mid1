package section4.mission1;

public class StringUtils {
    private StringUtils() {
    }

    public static boolean isAnagram(String str1, String str2) {
        if (notEqualsStringLength(str1, str2)) {
            return false;
        }

        int[] count = new int[256];
        for (int i = 0; i < str1.length(); i++) {
            count[str1.charAt(i)]++;
            count[str2.charAt(i)]--;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean equalsStringLength(final String str1, final String str2) {
        return str1.length() == str2.length();
    }

    private static boolean notEqualsStringLength(final String str1, final String str2) {
        return !equalsStringLength(str1, str2);
    }
}
