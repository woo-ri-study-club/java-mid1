package section04;

public class Palindrome {
    public static void main(String[] args) {
        String input = "babdad";
        //bab 나 aba
        String answer=palindrome(input);
        System.out.println("answer = " + answer);
    }

    private static String palindrome(String input) {
        if (input.length()<3) {
            throw new IllegalArgumentException("팰린드롬을 포함하고 있지않습니다.");
        }
        char[] chInput = input.toCharArray();
        int start=0;
        int end=0;
        int[] max = new int[2];
        for (int i = 1; i < input.length()-1; i++) {
            start = i;
            end = i;
            int count = 1;
            while (start > 0 && end < input.length()-1) {
                start--;
                end++;
                if (chInput[start] != chInput[end]) {
                    break;
                }
                count+=2;

            }
            if (max[1] < count) {
                max[1] = count;
                max[0] = start;
            }
        }
        return input.substring(max[0], max[1]);
    }
}
