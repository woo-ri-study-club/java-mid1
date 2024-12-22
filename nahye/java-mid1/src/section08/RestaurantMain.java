package section08;

import java.time.LocalDate;
import java.util.List;

public class RestaurantMain {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        // 테이블 생성
        restaurant.createTable(1);
        restaurant.createTable(2);
        restaurant.createTable(3);
        restaurant.createTable(4);
        restaurant.createTable(5);

        // 예약
        restaurant.reserve(1,"2024/11/01","KIM","NONE");
        restaurant.reserve(2,"2024/12/25","LEE","NONE");
        restaurant.reserve(3,"2024/12/25","HONG","NONE");
        restaurant.reserve(4,"2024/12/25","PARK","NONE");
        restaurant.reserve(5,"2024/12/25","PARK","NONE");
        // 취소
        restaurant.cancel(4);

        System.out.println("\n=== 전체 테이블 현황 ===");
        restaurant.displayAllTableList();

        System.out.println("\n==== 예약가능한 테이블 ====");
        restaurant.displayAvailableTableList();

        System.out.println("\n==== 전체 예약 현황 ====");
        restaurant.displayReserveList();

        System.out.println("\n=== 크리스마스 예약 현황 ===");
        restaurant.showResultByCondition(new Condition() {
            @Override
            public void check() {
                LocalDate targetDate = LocalDate.of(2024, 12, 25);
                List<Restaurant.Reservation> reservations = restaurant.findReservationsByDate(targetDate);
                reservations.forEach(System.out::println);
                System.out.println("2024년 12월 25일의 예약 건수: " + reservations.size());
            }
        });

        System.out.println("\n=== 특정 고객 예약 조회 ===");
        restaurant.showResultByCondition(new Condition() {
            @Override
            public void check() {
                String targetName = "LEE";
                System.out.println(targetName + "님의 예약 현황:");
                restaurant.findReservationsByName(targetName)
                        .forEach(System.out::println);
            }
        });
    }
}
