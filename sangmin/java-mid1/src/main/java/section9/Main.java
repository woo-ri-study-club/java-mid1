package section9;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant(30);
        restaurant.reserve(1,"nasang","010-1234-5678","2021-08-01 18:00");
        restaurant.reserve(2,"nasang","010-1234-5678","2021-08-01 18:00");

        restaurant.cancel(1);

        restaurant.getReservations().stream().forEach(reservation -> {
            System.out.println(reservation.getTableNumber());
        });
    }
}
