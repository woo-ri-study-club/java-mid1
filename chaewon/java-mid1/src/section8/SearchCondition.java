package section8;

import java.time.LocalDateTime;

@FunctionalInterface
public interface SearchCondition {
    boolean execute(Restaurant.Table table);
}
