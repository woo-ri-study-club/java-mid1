package section3;

public class Member {

    private final String id;

    private final String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("회원 [id:'%s', name:'%s']", this.id, this.name);
    }
}
