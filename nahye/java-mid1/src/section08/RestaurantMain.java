package section08;

import java.time.LocalDate;

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
        // 취소
        restaurant.cancel(4);

        restaurant.displayAvailableTableList();
        restaurant.displayReserveList();


        restaurant.showResultByCondition(new Condition() {
            @Override
            public void check() {
                int count = 0;
                for (Restaurant.Reservation reservation : restaurant.getReserveList().values()) {
                    if (reservation.getReserveDate().equals(LocalDate.of(2024, 12, 25))) {
                        count++;
                        System.out.println(reservation);
                    }
                }
                System.out.println("2024년 12월 25일의 예약 건수: " + count);
            }
        });

        restaurant.showResultByCondition(new Condition() {
            @Override
            public void check() {
                String targetName = "LEE";
                System.out.println(targetName + "님의 예약 현황:");
                for (Restaurant.Reservation reservation : restaurant.getReserveList().values()) {
                    if (reservation.getReserveName().equals(targetName)) {
                        System.out.println(reservation);
                    }
                }
            }
        });
    }
}
