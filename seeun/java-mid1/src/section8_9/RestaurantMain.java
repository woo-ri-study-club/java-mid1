package section8_9;

import static section8_9.Restaurant.search;

public class RestaurantMain {

    public static void main(String[] args) {

        Restaurant restaurant = new Restaurant();
        restaurant.addTable();
        restaurant.addTable();
        restaurant.addTable();

        restaurant.addReservation("에약자1", 1);
        restaurant.addReservation("에약자2", 3);

        search(() -> {
            restaurant.getReservations().forEach(System.out::println);
        });
    }

}
