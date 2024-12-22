package section9;

import java.util.function.Consumer;

public class ViewHandler {
    public static void view(Consumer<Restaurant> consumer, Restaurant restaurant) {
        consumer.accept(restaurant);
    }
}
