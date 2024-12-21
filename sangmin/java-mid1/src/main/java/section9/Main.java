package section9;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant(30);
        restaurant.reserve(1, "nasang", "010-1234-5678", "2021-08-01 18:00");
        restaurant.reserve(2, "nasang", "010-1234-5678", "2021-08-01 18:00");

        restaurant.cancel(1);

        restaurant.getReservations().stream().forEach(reservation -> {
            System.out.println(reservation.getTableNumber());
        });

        // 익명클래스로 내부 필드변경이 안되서 출력 함수로 구현
        restaurant.updateTableStatus(() -> {
            restaurant.getReservations().stream().forEach(reservation -> {
                System.out.println("reservation = " + reservation);
            });
        });

        restaurant.getReservations().stream().forEach(reservation -> {
            System.out.println(reservation.getTableNumber());
        });
    }
}
