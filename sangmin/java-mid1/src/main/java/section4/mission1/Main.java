package section4.mission1;

public class Main {
    public static void main(String[] args) {
        String str1 = "Listen";
        String str2 = "Silent";

        boolean result = StringUtils.isAnagram(str1, str2);

        System.out.println(result);
    }
}
