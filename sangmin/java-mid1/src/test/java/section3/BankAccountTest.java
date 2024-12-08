package section3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    @DisplayName("특정 금액을 입금하면 입금 후 금액이 조회된다.")
    @Test
    void deposit() {
        //given
        BankAccount bankAccount = new BankAccount("1234", "김철수", 1000);
        //when
        BankAccount depositedAccount = bankAccount.deposit(1000);
        //then
        assertThat(depositedAccount.getBalance()).isEqualTo(2000);
    }

    @DisplayName("음수 금액을 입금하면 오류 메시지가 발생한다.")
    @Test
    void depositNegativeAmount() {
        //given
        BankAccount bankAccount = new BankAccount("1234", "김철수", 1000);
        //when & then
        assertThrows(IllegalArgumentException.class,
                () -> bankAccount.deposit(-1),
                "0원 이하의 금액을 입금할 수 없습니다."
        );
    }

    @DisplayName("특정 금액을 출금하면 출금 후 금액이 조회된다.")
    @Test
    void withdraw() {
        //given
        BankAccount bankAccount = new BankAccount("1234", "김철수", 1000);
        //when
        BankAccount withdrawnAccount = bankAccount.withdraw(1000);
        //then
        assertThat(withdrawnAccount.getBalance()).isEqualTo(0);
    }

    @DisplayName("잔액보다 많은 금액을 출금하면 오류 메시지가 발생한다.")
    @Test
    void withdrawOverBalance() {
        //given
        BankAccount bankAccount = new BankAccount("1234", "김철수", 1000);
        //when & then
        assertThrows(IllegalArgumentException.class,
                () -> bankAccount.withdraw(1001),
                "잔액이 부족합니다."
        );
    }

    @DisplayName("음수 금액을 출금하면 오류 메시지가 발생한다.")
    @Test
    void withdrawNegativeAmount() {
        //given
        BankAccount bankAccount = new BankAccount("1234", "김철수", 1000);
        //when & then
        assertThrows(IllegalArgumentException.class,
                () -> bankAccount.withdraw(-1),
                "0원 이하의 금액을 출금할 수 없습니다."
        );
    }
}