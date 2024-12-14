package section6.domain.member.entity;

import java.util.Objects;

public record Member(String id, String name) {

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id(), member.id());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id());
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
