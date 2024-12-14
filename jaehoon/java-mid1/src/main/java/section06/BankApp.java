package section06;

public class BankApp {

  public static void main(String[] args) {

    // 사용자 & 계좌 생성
    Member gyeongseo = new Member("경서", new BankAccount("111-111", 10000));
    Member seogi = new Member("서기", new BankAccount("222-222", 10000));

    // ATM 생성
    Atm atm = new Atm();

    try {
      // 입금 테스트
      TransactionResult depositResult = atm.processTransaction(gyeongseo, null, "입금", 2000);
      System.out.println(depositResult.getTransactionSummary());
      printBalance(gyeongseo);
      System.out.println();

      // 출금 테스트
      TransactionResult withdrawResult = atm.processTransaction(gyeongseo, null, "출금", 1500);
      System.out.println(withdrawResult.getTransactionSummary());
      printBalance(gyeongseo);
      System.out.println();

      // 송금 성공 테스트
      TransactionResult transferResult1 = atm.processTransaction(gyeongseo, seogi, "송금", 3000);
      System.out.println(transferResult1.getTransactionSummary());
      printBalance(gyeongseo);
      printBalance(seogi);

      // 송금 실패 테스트
      TransactionResult transferResult2 = atm.processTransaction(gyeongseo, gyeongseo, "송금", 3000);
      System.out.println(transferResult2.getTransactionSummary());
      printBalance(gyeongseo);
      printBalance(seogi);

    } catch (Exception exception) {
      System.out.println();
      System.out.println("에러 메시지 = " + exception.getMessage());
    }
  }

  public static void printBalance(Member member) {
    System.out.printf("%s님의 계좌잔액은 `%.0f원` 입니다.\n", member.getName(), member.getBankAccount().getBalance());
  }
}
