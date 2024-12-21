package section8_9;

public class RestaurantMain {

    public static void main(String[] args) {

        Restaurant restaurant = new Restaurant();
        restaurant.addTable();
        restaurant.addTable();
        restaurant.addTable();

        restaurant.addReservation("에약자1", 1);
        restaurant.addReservation("에약자2", 3);

        restaurant.search(reservations ->
                reservations.forEach(System.out::println)
        );

    }

}
