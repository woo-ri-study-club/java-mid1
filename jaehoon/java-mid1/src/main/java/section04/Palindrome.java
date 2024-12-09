package study.section04;

public class Palindrome {

  public static void main(String[] args) {
    String input = "babad";
    String result = solution(input);
    System.out.println("result = " + result);
  }

  public static String solution(String input) {
    if (input == null || input.length() < 1) {
      return "";
    }

    int start = 0; // 가장 긴 팰린드롬 시작 인덱스
    int end = 0;   // 가장 긴 팰린드롬 끝 인덱스

    for (int i = 0; i < input.length(); i++) {
      // 홀수 길이 팰린드롬 확장
      int len1 = expandAroundCenter(input, i, i);
      // 짝수 길이 팰린드롬 확장
      int len2 = expandAroundCenter(input, i, i + 1);

      // 두 길이 중 더 긴 팰린드롬 선택
      int maxLength = Math.max(len1, len2);

      if (maxLength > (end - start)) {
        // 새로운 팰린드롬의 시작 및 끝 인덱스 갱신
        start = i - (maxLength - 1) / 2;
        end = i + maxLength / 2;
      }
    }

    // 가장 긴 팰린드롬 부분 문자열 반환
    return input.substring(start, end + 1);
  }

  // 중심을 기준으로 팰린드롬 확장
  private static int expandAroundCenter(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }
    // 팰린드롬 길이 반환
    return right - left - 1;
  }
}
