package section8_9;

public class RestaurantMain {

    public static void main(String[] args) {

        Restaurant restaurant1 = new Restaurant();
        restaurant1.addTable();
        restaurant1.addTable();
        restaurant1.addTable();

        restaurant1.addReservation("에약자1", 1);
        restaurant1.addReservation("에약자2", 3);

        restaurant1.searchReservation(reservations ->
                reservations.forEach(System.out::println)
        );

        Restaurant restaurant2 = new Restaurant();
        restaurant2.addTable();
        restaurant2.addTable();

        restaurant2.addReservation("예약자3", 2);

        restaurant2.searchTable(tables ->
                tables.stream()
                        .filter(Restaurant.Table::isBooked)
                        .forEach(System.out::println)
        );

    }

}
