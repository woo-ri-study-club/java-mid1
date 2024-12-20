package src.section9;

import java.time.LocalDateTime;
import java.util.function.Consumer;

public class RestaurantMain {
    public static void main(String[] args) {
        /*
        * Consumer는 Java의 java.util.function 패키지에 포함된 함수형 인터페이스 중 하나
        * 이 인터페이스는 하나의 인자를 받아서 아무런 결과를 반환하지 않는 함수형 프로그래밍 패턴을 지원
        * 주로 메서드의 인자로 전달하여 특정 작업을 수행하는 데 사용
        * */

        Restaurant restaurant = new Restaurant("나폴리 맛피아", 6);

        LocalDateTime start = LocalDateTime.of(2024, 12, 25, 12, 0, 0);
        Consumer<String> reservationAttempt = customerName -> {
            try {
                restaurant.tryReservation(customerName, start, start.plusHours(1), 3);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        reservationAttempt.accept("백종원");
        reservationAttempt.accept("안성재");

        new Consumer<String>() {
            @Override
            public void accept(String customerName) {
                try {
                    restaurant.tryReservation(customerName, start, start.plusHours(1), 5);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.accept("홍길동");
    }
}
