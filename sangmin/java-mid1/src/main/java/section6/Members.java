package section6;

import java.util.ArrayList;
import java.util.List;

public class Members {
    private final List<Member> members= new ArrayList<>();

    public void addMember(Member member) {
        members.add(member);
    }

    public Member findMember(long targetAccountNumber) {
        return members.stream()
                .filter(member -> member.equalsAccountNumber(targetAccountNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 계좌를 찾을 수 없습니다."));
    }
}
