package section9;

import java.time.LocalDateTime;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        restaurant.addTable(1, 4);
        restaurant.addTable(2, 6);

        LocalDateTime start1 = LocalDateTime.of(2024, 12, 20, 18, 0);
        LocalDateTime end1 = LocalDateTime.of(2024, 12, 20, 20, 0);

        LocalDateTime start2 = LocalDateTime.of(2024, 12, 20, 19, 0);
        LocalDateTime end2 = LocalDateTime.of(2024, 12, 20, 21, 0);

        boolean reservation1 = restaurant.getTableBy(1).addReservation("신짱구", start1, end1);
        boolean reservation2 = restaurant.getTableBy(1).addReservation("김철수", start2, end2);

        System.out.println("신짱구를 위한 예야기: " + reservation1);
        System.out.println("김철수를 위한 예야기: " + reservation2);

        System.out.println("6인석 테이블 리스트");

        // 익명 클래스를 보여주기 위하여 일부러 람다로 안 바꾸고 표현
        restaurant.findTables(new Predicate<Restaurant.Table>() {
            @Override
            public boolean test(Restaurant.Table table) {
                return table.getCapacity() == 6;
            }
        });

        System.out.println("신짱구의 예약 리스트: ");
        restaurant.getTables().stream()
                .flatMap(table -> table.getReservations().stream())
                .filter(reservation -> reservation.getCustomerName().equals("신짱구"))
                .forEach(System.out::println);

        restaurant.updateTableStatus(1, true);
        System.out.println("테이블 업데이트");
        restaurant.findTables(Restaurant.Table::isAvailable);
    }
}
