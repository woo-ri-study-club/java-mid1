package section6;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AccountNumberManager {
    private static final long MIN_ACCOUNT_NUMBER = 1000000000L;
    private static final long MAX_ACCOUNT_NUMBER = 9999999999L;

    private static AccountNumberManager instance;
    private Set<Long> uniqueNumbers = new HashSet<>();
    private Random random;

    private AccountNumberManager() {
        uniqueNumbers = new HashSet<>();
        random = new Random();
    }

    public static synchronized AccountNumberManager getInstance() {
        if (instance == null) {
            instance = new AccountNumberManager();
        }
        return instance;
    }

    public synchronized long generate() {
        if(uniqueNumbers.size() == MAX_ACCOUNT_NUMBER - MIN_ACCOUNT_NUMBER + 1) {
            throw new IllegalStateException("계좌번호를 더 이상 생성할 수 없습니다.");
        }
        long randomNumber;
        do {
            randomNumber = random.nextLong(MAX_ACCOUNT_NUMBER - MIN_ACCOUNT_NUMBER + 1) + MIN_ACCOUNT_NUMBER; // min ~ max 범위
        } while (uniqueNumbers.contains(randomNumber)); // 중복 확인
        uniqueNumbers.add(randomNumber); // 중복 저장
        return randomNumber;
    }
}
