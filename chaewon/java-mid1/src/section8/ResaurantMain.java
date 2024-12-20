package section8;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ResaurantMain {
    public static void main(String[] args) {
        Restaurant.Table tableA = new Restaurant.Table(1,4);
        Restaurant.Table tableB = new Restaurant.Table(2,6);
        Restaurant.Table tableC = new Restaurant.Table(3,8);

        Restaurant restaurant = new Restaurant(List.of(tableA, tableB, tableC));

        User userA = new User("유저A", "abcd1234@gamil.com", "010-1234-5678");
        User userB = new User("유저B", "1234abcd@gamil.com", "010-5678-1234");

        //정상 예약
        restaurant.addReservation(userA, tableA, LocalDate.of(2024, 12, 25), LocalTime.of(19, 0, 0), 3);
        restaurant.addReservation(userB, tableC, LocalDate.of(2024, 12, 25), LocalTime.of(19, 0, 0), 3);

//        //비정상 예약 - userA가 12/25에 또 예약을 시도할 경우
//        restaurant.addReservation(userA, tableA, LocalDate.of(2024,12,25), LocalTime.of(20,0,0),3);

//        //비정상 예약 - 수용 가능 인원보다 많은 수의 예약을 시도할 경우
//        restaurant.addReservation(userB, tableB, LocalDate.of(2024,12,25), LocalTime.of(20,0,0),7);

//        //비정상 예약 - 0명 이하로 예약 시도하는 경우
//        restaurant.addReservation(userB, tableB, LocalDate.of(2024,12,25), LocalTime.of(20,0,0),0);

//        //비정상 예약 - 이미 예약이 된 시간대의 테이블 예약 시도
//        restaurant.addReservation(userB, tableA, LocalDate.of(2024,12,25), LocalTime.of(19,0,0),3);

        //예약 수정
        restaurant.updateReservation(userA, LocalDate.of(2024, 12, 25), LocalTime.of(19, 0, 0), LocalDate.of(2024, 12, 26), LocalTime.of(19, 0, 0), 4);

        //예약 삭제
        restaurant.deleteReservation(userA, LocalDate.of(2024, 12, 26));

        //예약 검색
        List<Restaurant.Table> availableTables = restaurant.findAvailableTables(LocalDate.of(2024, 12, 26), LocalTime.of(19, 0, 0),
                //익명 클래스 학습
                new SearchCondition() {
                    @Override
                    public boolean execute(Restaurant.Table table) {
                        return table.getCapacity() >= 6;
                    }
                });

        for (Restaurant.Table availableTable : availableTables) {
            System.out.println("가능 번호 = " + availableTable.getTableNumber()); //B,C 테이블(2번, 3번)이 가능
        }

    }
}
